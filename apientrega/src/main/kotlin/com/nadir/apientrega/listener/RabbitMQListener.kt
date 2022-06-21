package com.nadir.apientrega.listener

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nadir.apientrega.entities.Entrega
import com.nadir.apientrega.services.EntregaService
import com.nadir.apientrega.utils.EXCHANGEPARAPRODUTO
import com.nadir.apientrega.utils.QUEUENAME
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.stereotype.Service

@Service
class RabbitMQListener(
    private val messageConverter: MessageConverter,
    private val entregaService: EntregaService,
    private val rabbitTemplate: RabbitTemplate
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = [QUEUENAME])
    fun receiveUserModelMessage(message : Message){
        log.info("receiving message from entrega model queue ${message.messageProperties.consumerQueue}")
        val compra = messageConverter.fromMessage(message).toString()
        var mapper = jacksonObjectMapper()
        val entrega = mapper.readValue<Entrega>(compra)
        log.info("$entrega")
        entregaService.salvar(entrega)

        //ENVIO PARA PRODUTO
        log.info("sending entrega to exchange product")
        //var mapper = jacksonObjectMapper()
        //val message = mapper.writeValueAsString(request.toCompraEntity(null))
        rabbitTemplate.convertAndSend(EXCHANGEPARAPRODUTO,"", message)
    }
}
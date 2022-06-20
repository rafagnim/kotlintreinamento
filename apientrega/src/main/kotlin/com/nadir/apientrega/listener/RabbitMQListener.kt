package com.nadir.apientrega.listener

import com.nadir.apicompras.entities.Compra
import com.nadir.apientrega.entities.Entrega
import com.nadir.apientrega.services.EntregaService
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class RabbitMQListener(
    private val messageConverter: MessageConverter,
    private val entregaService: EntregaService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = ["ENTREGA-QUEUE"])
    fun receiveUserModelMessage(message : Message){
        log.info("receiving message from entrega model queue ${message.messageProperties.consumerQueue}")
        //message.body?.let { String(it) }
        //log.info(messageConverter.fromMessage(message) as String?)
        val compra = messageConverter.fromMessage(message) as Compra
        //println(compra)
        //val userModel = messageConverter.fromMessage(message) as Compra
        log.info("$compra")
        val entrega: Entrega = Entrega(
            compra.idCliente, compra.idProduto, compra.qtdItensComprados, compra.valorUnitarioDoItem, compra.valorTotal
        )
        entregaService.salvar(entrega)
    }



}
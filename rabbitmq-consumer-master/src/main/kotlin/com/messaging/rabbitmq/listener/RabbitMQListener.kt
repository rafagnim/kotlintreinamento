package com.messaging.rabbitmq.listener

import com.google.gson.Gson
import com.messaging.rabbitmq.model.UserModel
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.stereotype.Service

@Service
class RabbitMQListener(
        private val messageConverter: MessageConverter
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = ["USER-MODEL-QUEUE"])
    fun receiveUserModelMessage(message : Message){
        log.info("receiving message from user model queue ${message.messageProperties.consumerQueue}")
        val body = message.body?.let { String(it) }
        val userModel = messageConverter.fromMessage(message) as UserModel
        log.info("$userModel")
    }



}
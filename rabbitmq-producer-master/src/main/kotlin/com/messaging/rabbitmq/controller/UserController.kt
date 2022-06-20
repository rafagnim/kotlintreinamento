package com.messaging.rabbitmq.controller

import com.google.gson.Gson
import com.messaging.rabbitmq.model.UserModel
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class UserController(
        private val rabbitTemplate: RabbitTemplate
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("queue/users")
    @ResponseStatus(HttpStatus.OK)
    fun postUserQueue(
            @RequestBody message : UserModel
    ){
        log.info("sending user to exchange")
        rabbitTemplate.convertAndSend("DIRECT-EXCHANGE","", message)
    }
}
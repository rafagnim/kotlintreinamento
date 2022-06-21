package com.nadir.apientrega.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.nadir.apientrega.utils.QUEUENAME
import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Bean
    fun messageConverter(objectMapper: ObjectMapper) : MessageConverter {
        return Jackson2JsonMessageConverter(objectMapper)
    }

    @Bean
    fun createUserQueue() : Queue?{
        return Queue(QUEUENAME, true)
    }
}
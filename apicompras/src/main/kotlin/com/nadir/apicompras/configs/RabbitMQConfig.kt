package com.nadir.apicompras.configs

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.*
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMQConfig {

    val exchangeName = "compra-entrega-exchange"

    val queueName = "ENTREGA-QUEUE"

    @Bean
    fun userModelBinding() : Binding{
        return BindingBuilder
            .bind(userModelQueue())
            .to(compraExchange())
            .with("")
            .noargs()
    }

    @Bean
    fun testBinding() : Binding{
        return BindingBuilder
            .bind(testQueue())
            .to(compraExchange())
            .with("")
            .noargs()
    }

    @Bean
    fun compraExchange() : Exchange{
        return ExchangeBuilder
            .directExchange(exchangeName)
            .durable(true)
            .build()
    }


    @Bean
    fun userModelQueue() : Queue {
        return QueueBuilder
            .durable(queueName)
            .build()
    }

    @Bean
    fun testQueue() : Queue {
        return QueueBuilder
            .durable("TEST-QUEUE")
            .build()
    }

    @Bean
    fun messageConverter(objectMapper: ObjectMapper) : MessageConverter{
        return Jackson2JsonMessageConverter(objectMapper)
    }
}
package com.messaging.rabbitmq.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.*
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMQConfig {

    @Bean
    fun userModelBinding() : Binding{
        return BindingBuilder
                .bind(userModelQueue())
                .to(directExchange())
                .with("")
                .noargs()
    }

    @Bean
    fun testBinding() : Binding{
        return BindingBuilder
                .bind(testQueue())
                .to(directExchange())
                .with("TEST-ROUTING-KEY")
                .noargs()
    }

    @Bean
    fun directExchange() : Exchange{
        return ExchangeBuilder
                .directExchange("DIRECT-EXCHANGE")
                .durable(true)
                .build()
    }


    @Bean
    fun userModelQueue() : Queue {
        return QueueBuilder
                .durable("USER-MODEL-QUEUE")
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
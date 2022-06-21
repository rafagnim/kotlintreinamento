package com.nadir.apicompras.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.nadir.apicompras.utils.EXCHANGENAME
import com.nadir.apicompras.utils.QUEUENAME
import com.nadir.apicompras.utils.TESTQUEUE
import org.springframework.amqp.core.*
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMQConfig {

    //val exchangeName = "compra-entrega-exchange"

    //val queueName = "ENTREGA-QUEUE"

    @Bean
    fun compraModelBinding() : Binding{
        return BindingBuilder
            .bind(compraModelQueue())
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
            .directExchange(EXCHANGENAME)
            .durable(true)
            .build()
    }


    @Bean
    fun compraModelQueue() : Queue {
        return QueueBuilder
            .durable(QUEUENAME)
            .build()
    }

    @Bean
    fun testQueue() : Queue {
        return QueueBuilder
            .durable(TESTQUEUE)
            .build()
    }

    @Bean
    fun messageConverter(objectMapper: ObjectMapper) : MessageConverter{
        return Jackson2JsonMessageConverter(objectMapper)
    }
}
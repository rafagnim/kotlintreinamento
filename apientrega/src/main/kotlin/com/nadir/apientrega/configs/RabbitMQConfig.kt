package com.nadir.apientrega.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.nadir.apientrega.utils.EXCHANGEPARAPRODUTO
import com.nadir.apientrega.utils.QUEUENAME
import com.nadir.apientrega.utils.QUEUEPARAPRODUTO
import org.springframework.amqp.core.*
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
    fun createCompraQueue() : Queue?{
        return Queue(QUEUENAME, true)
    }

    //============De entrega para Produtos

    @Bean
    fun entregaModelBinding() : Binding {
        return BindingBuilder
            .bind(entregaModelQueue())
            .to(entregaExchange())
            .with("")
            .noargs()
    }

    @Bean
    fun entregaExchange() : Exchange {
        return ExchangeBuilder
            .directExchange(EXCHANGEPARAPRODUTO)
            .durable(true)
            .build()
    }

    @Bean
    fun entregaModelQueue() : Queue {
        return QueueBuilder
            .durable(QUEUEPARAPRODUTO)
            .build()
    }
}
package com.nadir.mib.configuration

import com.nadir.mib.integration.feign.client.FeignErrorDecoder
import feign.Logger
import feign.okhttp.OkHttpClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["com.nadir.mib.integration.feign.client"])
class FeignConfig {
    @Bean
    fun errorDecoder(): FeignErrorDecoder? {
        return FeignErrorDecoder()
    }

    @Bean
    fun feignLoggerLevel(): Logger.Level? {
        return Logger.Level.FULL
    }

    //para utilizar PATCH no Feign
    @Configuration
    class FeignConfiguration {
        @Bean
        fun client(): OkHttpClient {
            return OkHttpClient()
        }
    }
}
package com.nadir.apicompras.configs

import com.nadir.apicompras.integration.feign.client.FeignErrorDecoder
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["com.nadir.apicompras.integration.feign.client"])
class FeignConfig {
    @Bean
    fun errorDecoder(): FeignErrorDecoder? {
        return FeignErrorDecoder()
    }
}
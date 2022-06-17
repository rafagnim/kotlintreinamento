package com.nadir.apiclientes.integration.feign.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "Apicep",
    url = "\${integration.apicep.url}"
)
interface EnderecoClient {
    @PostMapping("/api/v1/cep")
    fun buscaCEP(@RequestBody endereco: EnderecoId): String
}
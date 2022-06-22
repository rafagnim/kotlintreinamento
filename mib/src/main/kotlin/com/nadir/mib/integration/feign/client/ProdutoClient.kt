package com.nadir.mib.integration.feign.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "Apiprodutos",
    url = "\${integration.apiprodutos.url}"
)
interface ProdutoClient {
    @GetMapping("/api/v1/produtos")
    fun retrieveProdutos(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String): List<Produto>
}
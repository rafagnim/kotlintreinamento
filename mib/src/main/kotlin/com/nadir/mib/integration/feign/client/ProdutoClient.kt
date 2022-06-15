package com.nadir.mib.integration.feign.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "Apiprodutos",
    url = "\${integration.apiprodutos.url}"
)
interface ProdutoClient {
    @GetMapping("/api/v1/produtos")
    fun retrieveProdutos(): List<Produto>
}
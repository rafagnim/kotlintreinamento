package com.nadir.apicompras.integration.feign.client

import com.nadir.apicompras.requests.EstoqueRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import javax.validation.Valid

@FeignClient(
    name = "Apiprodutos",
    url = "\${integration.apiprodutos.url}"
)
interface ProdutoClient {
    @GetMapping("/api/v1/produtos/verificaestoque")
    fun verificaEstoqueProduto(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @RequestBody @Valid request: EstoqueRequest): Boolean
}
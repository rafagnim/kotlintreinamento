package com.nadir.mib.integration.feign.client

import com.nadir.mib.models.Produto
import com.nadir.mib.models.Usuario
import com.nadir.mib.requests.ProdutoRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@FeignClient(
    name = "Apiprodutos",
    url = "\${integration.apiprodutos.url}"
)
interface ProdutoClient {
    @GetMapping("/api/v1/produtos")
    fun retrieveProdutos(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String): List<Produto>

    @GetMapping("/api/v1/produtos/{id}")
    fun findById(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long ): Produto

    @PostMapping("/api/v1/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @RequestBody @Valid request: ProdutoRequest): ResponseEntity<Produto>

    @PatchMapping("api/v1/produtos/disable/{id}")
    fun disable(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long)

    @PatchMapping("api/v1/produtos/enable/{id}")
    fun enable(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long)
}
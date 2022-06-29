package com.nadir.mib.integration.feign.client

import com.nadir.mib.requests.CompraRequest
import com.nadir.mib.responses.CompraResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@FeignClient(
    name = "Apicompras",
    url = "\${integration.apicompras.url}"
)
interface CompraClient {
    @PostMapping("/api/v1/compras")
    fun create(
        @RequestHeader(value = "Authorization", required = true) authorizationHeader: String,
        @RequestBody @Valid request: CompraRequest
    ): Boolean

    @GetMapping("/api/v1/compras/{idCliente}")
    fun getAllComprasByIdCliente(
        @RequestHeader(value = "Authorization", required = true) authorizationHeader: String,
        @PathVariable idCliente: Long
    ): List<CompraResponse>

    @GetMapping("/api/v1/compras/{idCliente}/{idCompra}")
    fun getAllComprasByIdClienteAndByIdCompra(
        @RequestHeader(value = "Authorization", required = true) authorizationHeader: String,
        @PathVariable idCliente: Long, @PathVariable idCompra: UUID
    ): CompraResponse
}
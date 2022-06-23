package com.nadir.mib.integration.feign.client

import com.nadir.mib.models.Compra
import com.nadir.mib.requests.CompraRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import javax.validation.Valid

@FeignClient(
    name = "Apicompras",
    url = "\${integration.apicompras.url}"
)
interface CompraClient {
    @GetMapping("/api/v1/compras")
    fun create(
        @RequestHeader(value = "Authorization", required = true) authorizationHeader: String,
        @RequestBody @Valid request: CompraRequest
    ): ResponseEntity<Compra?>
}
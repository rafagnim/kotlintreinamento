package com.nadir.apicompras.controllers

import com.nadir.apicompras.entities.Compra
import com.nadir.apicompras.requests.CompraRequest
import com.nadir.apicompras.services.CompraService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/compras")
class CompraController (
    private val compraService: CompraService
        ) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: CompraRequest): ResponseEntity<Compra?> {
        return ResponseEntity.ok(compraService.salvar(request.toCompraEntity(null)))
    }
}
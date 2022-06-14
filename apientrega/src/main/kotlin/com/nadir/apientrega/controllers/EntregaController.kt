package com.nadir.apientrega.controllers

import com.nadir.apientrega.EntregaService
import com.nadir.apientrega.entities.Entrega
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/entregas")
class EntregaController (
    private val entregaService: EntregaService
        ) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: Entrega): Entrega? {
        return entregaService.salvar(request)
    }
}
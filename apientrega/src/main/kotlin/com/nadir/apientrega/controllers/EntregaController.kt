package com.nadir.apientrega.controllers

import com.nadir.apientrega.entities.Entrega
import com.nadir.apientrega.integration.feign.client.UsuarioClient
import com.nadir.apientrega.services.EntregaService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/v1/entregas")
class EntregaController (
    private val entregaService: EntregaService,
    private val usuarioClient: UsuarioClient
        ) {

    @GetMapping("/{idCliente}")
    fun getAllByIdClient(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable idCliente: Long) : List<Entrega> {
        usuarioClient.validaToken(authorizationHeader)
        return entregaService.getAllByUserId(idCliente)
    }

    @GetMapping("/{idCliente}/{id}")
    fun getAllByIdClient(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable idCliente: Long, @PathVariable id: UUID) : Entrega {
        usuarioClient.validaToken(authorizationHeader)
        return entregaService.getAllByUserIdAndId(idCliente, id)
    }
}
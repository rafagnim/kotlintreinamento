package com.nadir.apicompras.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nadir.apicompras.entities.Compra
import com.nadir.apicompras.integration.feign.client.UsuarioClient
import com.nadir.apicompras.requests.CompraRequest
import com.nadir.apicompras.services.CompraService
import com.nadir.apicompras.utils.EXCHANGENAME
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/compras")
class CompraController (
    private val compraService: CompraService,
    private val rabbitTemplate: RabbitTemplate,
    private val usuarioClient: UsuarioClient
        ) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @RequestBody @Valid request: CompraRequest): ResponseEntity<Compra?> {

        log.info("sending compra to exchange")
        var mapper = jacksonObjectMapper()
        val message = mapper.writeValueAsString(request.toCompraEntity(null))

        val email:String = usuarioClient.validaToken(authorizationHeader)
        if (email != "") {

            // Fazer reserva em Produtos???
            rabbitTemplate.convertAndSend(EXCHANGENAME,"", message)
            return ResponseEntity.ok(compraService.salvar(request.toCompraEntity(null)))
        } else {
            throw Exception("Token inválido")
        }
    }
}
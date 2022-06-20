package com.nadir.apicompras.controllers

import com.nadir.apicompras.entities.Compra
import com.nadir.UserModel
import com.nadir.apicompras.requests.CompraRequest
import com.nadir.apicompras.services.CompraService
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
    private val rabbitTemplate: RabbitTemplate
        ) {

    private val log = LoggerFactory.getLogger(javaClass)

    val exchangeName = "compra-entrega-exchange"

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: CompraRequest): ResponseEntity<Compra?> {

        log.info("sending compra to exchange")
        rabbitTemplate.convertAndSend(exchangeName,"", request.toCompraEntity(null))

        return ResponseEntity.ok(compraService.salvar(request.toCompraEntity(null)))
    }
}
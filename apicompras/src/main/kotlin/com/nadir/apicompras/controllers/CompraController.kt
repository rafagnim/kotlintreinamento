package com.nadir.apicompras.controllers

import com.fasterxml.jackson.databind.util.JSONPObject
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nadir.apicompras.entities.Compra
import com.nadir.UserModel
import com.nadir.apicompras.requests.CompraRequest
import com.nadir.apicompras.services.CompraService
import com.nadir.apicompras.utils.EXCHANGENAME
import org.apache.tomcat.util.json.JSONParser
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.json.GsonJsonParser
import org.springframework.data.mongodb.util.BsonUtils.toJson
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: CompraRequest): ResponseEntity<Compra?> {

        log.info("sending compra to exchange")
        var mapper = jacksonObjectMapper()
        val message = mapper.writeValueAsString(request.toCompraEntity(null))
        rabbitTemplate.convertAndSend(EXCHANGENAME,"", message)

        return ResponseEntity.ok(compraService.salvar(request.toCompraEntity(null)))
    }
}
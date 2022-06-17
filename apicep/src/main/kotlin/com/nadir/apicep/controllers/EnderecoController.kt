package com.nadir.apicep.controllers

import com.nadir.apicep.entities.EnderecoId
import com.nadir.apicep.requests.EnderecoIdRequest
import com.nadir.apicep.services.EnderecoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/cep")
class EnderecoController (
    private val enderecoService: EnderecoService
        ) {

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun getCEP(@RequestBody @Valid request: EnderecoIdRequest) : ResponseEntity<String> {
        return ResponseEntity.ok(enderecoService.obterCepPorEndereco(request.toEnderecoEntity(null)))
    }
}
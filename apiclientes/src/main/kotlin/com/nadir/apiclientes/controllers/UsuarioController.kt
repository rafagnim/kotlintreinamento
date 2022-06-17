package com.nadir.apiclientes.controllers

import com.nadir.apiclientes.entities.Endereco
import com.nadir.apiclientes.entities.Usuario
import com.nadir.apiclientes.integration.feign.client.EnderecoClient
import com.nadir.apiclientes.integration.feign.client.EnderecoId
import com.nadir.apiclientes.requests.UsuarioRequest
import com.nadir.apiclientes.services.UsuarioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collector
import javax.validation.Valid
import kotlin.streams.toList

@RestController
@RequestMapping("api/v1/usuarios")
class UsuarioController (
    private val usuarioService: UsuarioService,
    private val enderecoClient: EnderecoClient
        ) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: UsuarioRequest): ResponseEntity<Usuario> {
        request.enderecos = this.buscaCEPs(request.enderecos);
        return ResponseEntity.ok(usuarioService.save(request.toUsuarioEntity(null)))
    }

    @GetMapping
    fun getAll() : ResponseEntity<List<Usuario>> {
        return ResponseEntity.ok(usuarioService.findAll())
    }

    private fun buscaCEPs(enderecos: List<Endereco>?): List<Endereco>? {
        if (enderecos != null) {
            enderecos.forEach {
                var end: EnderecoId = EnderecoId(it.uf, it.municipio, it.logradouro)
                var cep: String = enderecoClient.buscaCEP(end)
                it.cep = cep
            }
        }
        return enderecos
    }
}
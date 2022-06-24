package com.nadir.apiclientes.controllers

import com.nadir.apiclientes.entities.Endereco
import com.nadir.apiclientes.entities.Usuario
import com.nadir.apiclientes.integration.feign.client.EnderecoClient
import com.nadir.apiclientes.integration.feign.client.EnderecoId
import com.nadir.apiclientes.requests.PostUserRequest
import com.nadir.apiclientes.services.UsuarioService
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/usuarios")
class UsuarioController (
    private val usuarioService: UsuarioService,
    private val enderecoClient: EnderecoClient
        ) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll() : List<Usuario> {
        val usuario = SecurityContextHolder.getContext().authentication.principal
        return usuarioService.getAll()
    }

    @GetMapping("validatoken")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun verificaValidadeToken(): Long {
        return SecurityContextHolder.getContext().authentication.principal.toString().toLong()
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request : PostUserRequest){
        request.enderecos = this.buscaCEPs(request.enderecos);
        return usuarioService.save(request)
    }

    @PatchMapping("/{id}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun disable(@PathVariable id : Long){
        return usuarioService.disable(id)
    }

    @PatchMapping("/{id}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun enable(@PathVariable id : Long){
        return usuarioService.enable(id)
    }

    private fun buscaCEPs(enderecos: List<Endereco>?): List<Endereco>? {
        enderecos?.forEach {
            val end: EnderecoId = EnderecoId(it.uf, it.municipio, it.logradouro)
            val cep: String = enderecoClient.buscaCEP(end)
            it.cep = cep
        }
        return enderecos
    }
}
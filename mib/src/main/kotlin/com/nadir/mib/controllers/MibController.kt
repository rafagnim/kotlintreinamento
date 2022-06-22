package com.nadir.mib.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nadir.mib.integration.feign.client.Produto
import com.nadir.mib.integration.feign.client.ProdutoClient
import com.nadir.mib.integration.feign.client.UsuarioClient
import com.nadir.mib.models.Token
import com.nadir.mib.models.Usuario
import com.nadir.mib.requests.LoginRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiParam
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("shop")
class MibController (
    val produtoClient: ProdutoClient,
    val usuarioClient: UsuarioClient
        ) {
    @ApiImplicitParam(value = "Exemplo TOKEN: Bearer 9e878e...")
    @GetMapping("produtos")
    fun retrieveProdutos(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String) : List<Produto>
    {
        return produtoClient.retrieveProdutos(authorizationHeader)
    }

    @ApiImplicitParam(value = "Exemplo TOKEN: Bearer 9e878e...")
    @GetMapping("usuarios")
    fun retrieveUsuarios(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String) : List<Usuario>
    {
        val auth = authorizationHeader
        return usuarioClient.getAll(authorizationHeader)
    }

    @PostMapping("login")
    fun login(@RequestBody usuario: LoginRequest): Token {
        var mapper = jacksonObjectMapper()
        return mapper.readValue(usuarioClient.login(usuario))
    }
}
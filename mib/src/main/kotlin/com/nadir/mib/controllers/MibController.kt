package com.nadir.mib.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nadir.mib.exceptions.EstoqueInsuficenteException
import com.nadir.mib.integration.feign.client.*
import com.nadir.mib.models.Compra
import com.nadir.mib.models.Token
import com.nadir.mib.models.Usuario
import com.nadir.mib.requests.CompraRequest
import com.nadir.mib.requests.LoginRequest
import com.nadir.mib.requests.ProdutoRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.security.sasl.AuthenticationException
import javax.validation.Valid


@RestController
@RequestMapping("shop")
class MibController (
    val produtoClient: ProdutoClient,
    val usuarioClient: UsuarioClient,
    val compraClient: CompraClient
        ) {

    @PostMapping("criarproduto")
    @ResponseStatus(HttpStatus.CREATED)
    fun criarProduto(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @RequestBody @Valid request: ProdutoRequest): ResponseEntity<Produto> {
        return produtoClient.create(authorizationHeader, request)
    }

    @GetMapping("produtos")
    fun retrieveProdutos(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String) : List<Produto>
    {
        return produtoClient.retrieveProdutos(authorizationHeader)
    }

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

    @PostMapping("compra")
    fun compra (@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @RequestBody @Valid request: CompraRequest): ResponseEntity<CompraRequest> {
        try {
            val retorno = compraClient.create(authorizationHeader, request)
            if (retorno) {
                return ResponseEntity.ok(request)
            } else {
                throw EstoqueInsuficenteException("Estoque Insuficiente")
            }
        } catch (ex: AuthenticationException) {
            throw AuthenticationException(ex.message)
        }
    }
}
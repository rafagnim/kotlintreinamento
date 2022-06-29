package com.nadir.mib.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nadir.mib.exceptions.EstoqueInsuficenteException
import com.nadir.mib.integration.feign.client.*
import com.nadir.mib.models.Produto
import com.nadir.mib.models.Token
import com.nadir.mib.models.Usuario
import com.nadir.mib.requests.CompraRequest
import com.nadir.mib.requests.LoginRequest
import com.nadir.mib.requests.PostUserRequest
import com.nadir.mib.requests.ProdutoRequest
import com.nadir.mib.responses.CompraResponse
import com.nadir.mib.responses.UsuarioResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
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

    @GetMapping("produtos/{id}")
    fun findProdutoById(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long ): Produto {
        var prod = produtoClient.findById(authorizationHeader, id)
        return produtoClient.findById(authorizationHeader, id)
    }

    @PatchMapping("/produtos/disable/{id}")
    fun disableProduto(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long) {
        return produtoClient.disable(authorizationHeader, id)
    }

    @PatchMapping("api/v1/produtos/enable/{id}")
    fun enableProduto(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long) {
        return produtoClient.enable(authorizationHeader, id)
    }


    @GetMapping("usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    fun retrieveUsuarios(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String) : List<Usuario>
    {
        val auth = authorizationHeader
        return usuarioClient.getAll(authorizationHeader)
    }

    @PostMapping("novousuario")
    fun createUser(@RequestBody @Valid request : PostUserRequest) {
        return usuarioClient.create(request)
    }

    @GetMapping("usuarios/{id}")
    fun findUsuarioById(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long ): UsuarioResponse {
        return usuarioClient.findById(authorizationHeader, id)
    }

    @PatchMapping("enableuser/{id}")
    fun enableUser(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long) {
        usuarioClient.enable(authorizationHeader, id)
    }

    @PatchMapping("disableuser/{id}")
    fun disableUser(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long) {
        usuarioClient.disable(authorizationHeader, id)
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
                throw EstoqueInsuficenteException("Estoque Insuficiente", 422)
            }
        } catch (ex: AuthenticationException) {
            throw AuthenticationException(ex.message)
        }
    }

    @GetMapping("/api/v1/compras/{idCliente}")
    fun getAllComprasByIdCliente(
        @RequestHeader(value = "Authorization", required = true) authorizationHeader: String,
        @PathVariable idCliente: Long
    ): List<CompraResponse> {
        return compraClient.getAllComprasByIdCliente(authorizationHeader, idCliente)
    }

    @GetMapping("/api/v1/compras/{idCliente}/{idCompra}")
    fun getAllComprasByIdClienteAndByIdCompra(
        @RequestHeader(value = "Authorization", required = true) authorizationHeader: String,
        @PathVariable idCliente: Long, @PathVariable idCompra: UUID
    ): CompraResponse {
        return compraClient.getAllComprasByIdClienteAndByIdCompra(authorizationHeader, idCliente, idCompra)
    }
}
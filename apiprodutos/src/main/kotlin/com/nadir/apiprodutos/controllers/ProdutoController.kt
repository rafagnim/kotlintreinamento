package com.nadir.apiprodutos.controllers

import com.nadir.apiprodutos.entities.Produto
import com.nadir.apiprodutos.exceptions.AuthenticationException
import com.nadir.apiprodutos.integration.feign.client.UsuarioClient
import com.nadir.apiprodutos.requests.EstoqueRequest
import com.nadir.apiprodutos.requests.ProdutoRequest
import com.nadir.apiprodutos.services.ProdutoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/produtos")
class ProdutoController (
    private val produtoService: ProdutoService,
    private val usuarioClient: UsuarioClient
        ) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @RequestBody @Valid request: ProdutoRequest): ResponseEntity<Produto> {
        usuarioClient.validaToken(authorizationHeader)
        return ResponseEntity.ok(produtoService.save(request.toProdutoEntity(null)))
    }

    @GetMapping
    fun getAll(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String) : ResponseEntity<List<Produto>>{
        val clienteId:Long = usuarioClient.validaToken(authorizationHeader)
        return ResponseEntity.ok(produtoService.findAll())
    }

    @GetMapping("/{id}")
    fun getById(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long) : ResponseEntity<Produto>{
        val clienteId:Long = usuarioClient.validaToken(authorizationHeader)
        return ResponseEntity.ok(produtoService.findById(id))
    }

    @PostMapping("verificaestoque")
    fun verificaEstoque(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @RequestBody @Valid request: EstoqueRequest): Boolean {
        val clienteID: Long = usuarioClient.validaToken(authorizationHeader)
        val produto: Produto = produtoService.findById(request.idProduto)
        if (produto.quantidade >= request.qtdItensComprados) {
            produto.quantidade = produto.quantidade.minus(request.qtdItensComprados)
            produto.quantidadeReservadaCarrinho = produto.quantidadeReservadaCarrinho?.plus(request.qtdItensComprados)
            produtoService.save(produto)
            return true
        } else {
            return false
        }
    }

    @PatchMapping("/disable/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun disable(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long){
        val clienteID: Long = usuarioClient.validaToken(authorizationHeader)
        if (clienteID == 1L) {
            return produtoService.disable(id)
        } else throw throw AuthenticationException("Usuário não autorizado")

    }

    @PatchMapping("/enable/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun enable(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long){
        val clienteID: Long = usuarioClient.validaToken(authorizationHeader)
        if (clienteID == 1L) {
            return produtoService.enable(id)
        } else throw throw AuthenticationException("Usuário não autorizado")
    }
}
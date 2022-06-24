package com.nadir.apiprodutos.controllers

import com.nadir.apiprodutos.entities.Produto
import com.nadir.apiprodutos.integration.feign.client.UsuarioClient
import com.nadir.apiprodutos.requests.EstoqueRequest
import com.nadir.apiprodutos.requests.ProdutoRequest
import com.nadir.apiprodutos.services.ProdutoService
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

//- Para que um usuário possa cadastrar/comprar um produto, ele deve ter feito login previamente;

@RestController
@RequestMapping("api/v1/produtos")
class ProdutoController (
    private val produtoService: ProdutoService,
    private val usuarioClient: UsuarioClient
        ) {
//    -> POST /products - Inserir novo produto na base;
//    -> PATCH /products/{id}/deactivate - Desativa o produto na base;
//    -> PATCH /products/{id}/activate - Ativa o produtoo na base;
//    -> GET /products - Recupera todos os produtos ativos da base;
//    -> GET /products/{id} - Recupera o produto referente à id informada;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @RequestBody @Valid request: ProdutoRequest): ResponseEntity<Produto> {
        return ResponseEntity.ok(produtoService.save(request.toProdutoEntity(null)))
    }

    @GetMapping
    fun getAll(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String) : ResponseEntity<List<Produto>>{
        val clienteId:Long = usuarioClient.validaToken(authorizationHeader)
        //if (email != "") {
            return ResponseEntity.ok(produtoService.findAll())
        //} else {
            //throw Exception("Token inválido")
        //}
    }

    @PostMapping("verificaestoque")
    fun verificaEstoque(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @RequestBody @Valid request: EstoqueRequest): Boolean {
        val clienteID: Long = usuarioClient.validaToken(authorizationHeader)
        //if (email != "") {
            val produto: Produto = produtoService.findById(request.idProduto)
            if (produto.quantidade >= request.qtdItensComprados) {
                produto.quantidade = produto.quantidade.minus(request.qtdItensComprados)
                produto.quantidadeReservadaCarrinho = produto.quantidadeReservadaCarrinho?.plus(request.qtdItensComprados)
                produtoService.save(produto)
                return true
            } else {
                return false
            }
        //} else {
        //    throw Exception("Token inválido")
        //}
    }
}
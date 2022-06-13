package com.nadir.apiprodutos.controllers

import com.nadir.apiprodutos.services.ProdutoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

//- Para que um usuário possa cadastrar/comprar um produto, ele deve ter feito login previamente;

@RestController
@RequestMapping("api/v1/produtos")
class ProdutoController (
    private val produtoService: ProdutoService
        ) {
//    -> POST /products - Inserir novo produto na base;
//    -> PATCH /products/{id}/deactivate - Desativa o produto na base;
//    -> PATCH /products/{id}/activate - Ativa o produtoo na base;
//    -> GET /products - Recupera todos os produtos ativos da base;
//    -> GET /products/{id} - Recupera o produto referente à id informada;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: ProdutoRequest) {
        produtoService.save(request.toBookEntity(null))

    }
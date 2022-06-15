package com.nadir.mib.controllers

import com.nadir.mib.integration.feign.client.Produto
import com.nadir.mib.integration.feign.client.ProdutoClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("shop")
class MibController (
    val produtoClient: ProdutoClient
        ) {
    @GetMapping
    fun retrieveProdutos() : List<Produto>
    {
        return produtoClient.retrieveProdutos()
    }
}
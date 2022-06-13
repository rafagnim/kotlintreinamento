package com.nadir.apiprodutos.services

import com.nadir.apiprodutos.entities.Produto
import com.nadir.apiprodutos.repositories.ProdutoRepository
import org.springframework.stereotype.Service

@Service
class ProdutoService (
    private val produtoRepository: ProdutoRepository
        ) {
    fun save(produto: Produto) : Produto {
        return produtoRepository.save(produto)
    }
}
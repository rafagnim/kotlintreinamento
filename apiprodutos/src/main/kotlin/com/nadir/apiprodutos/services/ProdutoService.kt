package com.nadir.apiprodutos.services

import com.nadir.apiprodutos.entities.Produto
import com.nadir.apiprodutos.exceptions.EstoqueNaoZeradoException
import com.nadir.apiprodutos.exceptions.NotFoundException
import com.nadir.apiprodutos.repositories.ProdutoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ProdutoService (
    private val produtoRepository: ProdutoRepository
        ) {
    @CacheEvict(allEntries = true, cacheNames = ["getAll"])
    fun save(produto: Produto) : Produto {
        return produtoRepository.save(produto)
    }

    @Cacheable("getAll")
    fun findAll() : List<Produto>{
        return produtoRepository.findAll()
    }

    fun disable(id: Long) {
        val produto = produtoRepository.findById(id).orElseThrow {
            NotFoundException(
                "Product with id %s not exists.".format(
                    id
                )
            )
        }

        if (produto.quantidade.compareTo(BigDecimal.ZERO) == 0 && produto.quantidadeReservadaCarrinho.compareTo(BigDecimal.ZERO) == 0) {
            produto.isActive = false
            produtoRepository.save(produto)
        } else {
            throw EstoqueNaoZeradoException("O estoque precisa estar zerado para desativar o produto")
        }

    }

    fun enable(id: Long) {
        val produto = produtoRepository.findById(id).orElseThrow { NotFoundException("User with id %s not exists.".format(id)) }
        produto.isActive = true
        produtoRepository.save(produto)
    }

    fun findById(id: Long): Produto {
        return produtoRepository.findById(id).orElseThrow()
    }
}
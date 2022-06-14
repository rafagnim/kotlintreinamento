package com.nadir.apiprodutos.requests

import com.nadir.apiprodutos.entities.Produto
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty

data class ProdutoRequest (
    @field:NotEmpty(message = "Obrigatório informar o nome")
    val nome: String,
    //@field:NotEmpty(message = "Obrigatório informar a quantidade")
    val quantidade: BigDecimal,
    //@field:NotEmpty(message = "Obrigatório informar o valor unitário")
    val valorUnitario: BigDecimal,
    @field:NotEmpty(message = "Obrigatório informar a descrição")
    val descricao: String,
        ){
    fun toBookEntity(produto: Produto?): Produto {
        return if (produto == null)
            Produto(
                nome = this.nome,
                quantidade = this.quantidade,
                valorUnitario = this.valorUnitario,
                descricao = this.descricao,
                isActive = true
            )
        else
            Produto(
                id = produto.id,
                nome = produto.nome,
                quantidade = produto.quantidade,
                valorUnitario = produto.valorUnitario,
                descricao = produto.descricao,
                isActive =  produto.isActive
            )
    }
}

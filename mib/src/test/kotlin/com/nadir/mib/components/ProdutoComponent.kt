package com.nadir.mib.components

import com.nadir.mib.models.Produto
import java.math.BigDecimal

class ProdutoComponent {
    companion object {
        fun createProduto(
            id: Long = (1..5000).random().toLong(),
            nome: String = "Produto$id",
            quantidade: BigDecimal = BigDecimal.TEN,
            quantidadeReservadaCarrinho: BigDecimal = BigDecimal.ZERO,
            valorUnitario: BigDecimal = BigDecimal.valueOf(10.10),
            descricao: String = "Produto descrição",
            isActive: Boolean = true
        ) : Produto {
            return Produto(id, nome, quantidade, quantidadeReservadaCarrinho, valorUnitario, descricao, isActive)
        }
    }
}
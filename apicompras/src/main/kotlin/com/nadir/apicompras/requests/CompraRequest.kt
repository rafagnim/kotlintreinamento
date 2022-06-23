package com.nadir.apicompras.requests

import com.nadir.apicompras.entities.Compra
import java.math.BigDecimal

class CompraRequest (
    var idCliente: Long?,
    val idProduto: Long,
    val qtdItensComprados: BigDecimal,
    val valorUnitarioDoItem: BigDecimal,
    val valorTotal: BigDecimal?
        ) {

        fun toCompraEntity(compra: Compra?, idCliente: Long): Compra {
            return if (compra == null)
                Compra(
                    idCliente = idCliente,
                    idProduto = this.idProduto,
                    qtdItensComprados = this.qtdItensComprados,
                    valorUnitarioDoItem = this.valorUnitarioDoItem,
                    valorTotal = this.valorUnitarioDoItem.multiply(BigDecimal.valueOf(qtdItensComprados.toLong()))
                )
            else
                Compra(
                    idCliente = compra.idCliente,
                    idProduto = compra.idProduto,
                    qtdItensComprados = compra.qtdItensComprados,
                    valorUnitarioDoItem = compra.valorUnitarioDoItem,
                    valorTotal = compra.valorTotal
                )
        }
}


package com.nadir.apicompras.requests

import com.nadir.apicompras.entities.Compra
import java.math.BigDecimal

class CompraRequest (
    val idCliente: Long,
    val idProduto: Long,
    val qtdItensComprados: Integer,
    val valorUnitarioDoItem: BigDecimal,
    val valorTotal: BigDecimal?
        ) {

        fun toCompraEntity(compra: Compra?): Compra {
            return if (compra == null)
                Compra(
                    idCliente = this.idCliente,
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


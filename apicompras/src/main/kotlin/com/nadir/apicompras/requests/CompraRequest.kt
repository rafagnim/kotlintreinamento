package com.nadir.apicompras.requests

import com.nadir.apicompras.entities.Compra
import com.nadir.apicompras.entities.Entrega
import java.math.BigDecimal
import java.util.*

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
                    id = UUID.randomUUID(),
                    idCliente = idCliente,
                    idProduto = this.idProduto,
                    qtdItensComprados = this.qtdItensComprados,
                    valorUnitarioDoItem = this.valorUnitarioDoItem,
                    valorTotal = this.valorUnitarioDoItem.multiply(BigDecimal.valueOf(qtdItensComprados.toLong()))
                )
            else
                Compra(
                    id = compra.id,
                    idCliente = compra.idCliente,
                    idProduto = compra.idProduto,
                    qtdItensComprados = compra.qtdItensComprados,
                    valorUnitarioDoItem = compra.valorUnitarioDoItem,
                    valorTotal = compra.valorTotal
                )
        }

        fun toEntregaEntity(idCliente: Long): Entrega {
            return Entrega (
                idCliente = idCliente,
                idProduto = this.idProduto,
                qtdItensComprados = this.qtdItensComprados,
                valorUnitarioDoItem = this.valorUnitarioDoItem,
                valorTotal = this.valorUnitarioDoItem.multiply(BigDecimal.valueOf(qtdItensComprados.toLong()))
                    )
        }
}


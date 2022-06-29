package com.nadir.apicompras.entities

import com.nadir.apicompras.reponses.CompraResponse
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.*

@Document(collection = "compras")
data class Compra (
    @Id
    val id: UUID,
    val idCliente: Long,
    val idProduto: Long,
    val qtdItensComprados: BigDecimal,
    val valorUnitarioDoItem: BigDecimal,
    val valorTotal: BigDecimal
        ) {
    fun toResponse() : CompraResponse {
        return CompraResponse (
            id = this.id,
            idCliente = this.idCliente,
            idProduto = this.idProduto,
            qtdItensComprados = this.qtdItensComprados,
            valorUnitarioDoItem = this.valorUnitarioDoItem,
            valorTotal = this.valorTotal
                )
    }
}
package com.nadir.apicompras.entities

import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection = "compras")
data class Compra (
    val idCliente: Long,
    val idProduto: Long,
    val qtdItensComprados: Integer,
    val valorUnitarioDoItem: BigDecimal,
    val valorTotal: BigDecimal
        ) {
}
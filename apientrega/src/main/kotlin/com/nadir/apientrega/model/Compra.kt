package com.nadir.apicompras.entities

import java.math.BigDecimal

data class Compra(
    val idCliente: Long,
    val idProduto: Long,
    val qtdItensComprados: Integer,
    val valorUnitarioDoItem: BigDecimal,
    val valorTotal: BigDecimal
)
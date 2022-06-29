package com.nadir.apicompras.entities

import java.math.BigDecimal

data class Entrega (
    val idCliente: Long?,
    val idProduto: Long,
    val qtdItensComprados: BigDecimal,
    val valorUnitarioDoItem: BigDecimal,
    val valorTotal: BigDecimal?
)
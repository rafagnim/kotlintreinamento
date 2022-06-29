package com.nadir.mib.responses

import java.math.BigDecimal

data class CompraResponse (
    val id: Any,
    val idCliente: Long,
    val idProduto: Long,
    val qtdItensComprados: BigDecimal,
    val valorUnitarioDoItem: BigDecimal,
    val valorTotal: BigDecimal
)
package com.nadir.mib.requests

import java.math.BigDecimal

data class ProdutoRequest (
    val nome: String,
    val quantidade: BigDecimal,
    val valorUnitario: BigDecimal,
    val descricao: String,
)
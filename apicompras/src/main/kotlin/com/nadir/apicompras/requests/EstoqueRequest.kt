package com.nadir.apicompras.requests

import java.math.BigDecimal

data class EstoqueRequest (
    val idProduto: Long,
    val qtdItensComprados: BigDecimal
)
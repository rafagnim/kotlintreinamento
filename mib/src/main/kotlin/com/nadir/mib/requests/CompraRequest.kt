package com.nadir.mib.requests

import java.math.BigDecimal

class CompraRequest (
    val idProduto: Long,
    val qtdItensComprados: Integer,
    val valorUnitarioDoItem: BigDecimal
)
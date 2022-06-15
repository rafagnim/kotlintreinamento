package com.nadir.mib.integration.feign.client

import java.math.BigDecimal

data class Produto (

    val id: Long? = null,

    var nome: String,

    var quantidade: BigDecimal,

    var valorUnitario: BigDecimal,

    var descricao: String,

    var isActive: Boolean

) {
}
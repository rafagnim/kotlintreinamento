package com.nadir.mib.models

import java.math.BigDecimal

data class Produto (

    val id: Long? = null,

    var nome: String,

    var quantidade: BigDecimal,

    var quantidadeReservadaCarrinho: BigDecimal?,

    var valorUnitario: BigDecimal,

    var descricao: String,

    var isActive: Boolean

) {
}
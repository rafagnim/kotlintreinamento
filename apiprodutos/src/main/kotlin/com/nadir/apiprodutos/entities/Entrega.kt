package com.nadir.apiprodutos.entities

import java.math.BigDecimal

data class Entrega(
    val idCliente: Long,
    val idProduto: Long,
    val qtdItensComprados: Integer,
    val valorUnitarioDoItem: BigDecimal,
    val valorTotal: BigDecimal) {}
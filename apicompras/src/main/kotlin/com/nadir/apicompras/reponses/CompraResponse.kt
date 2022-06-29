package com.nadir.apicompras.reponses

import org.bson.types.ObjectId
import java.math.BigDecimal
import java.util.*

data class CompraResponse (
    val id: Any,
    val idCliente: Long,
    val idProduto: Long,
    val qtdItensComprados: BigDecimal,
    val valorUnitarioDoItem: BigDecimal,
    val valorTotal: BigDecimal
)
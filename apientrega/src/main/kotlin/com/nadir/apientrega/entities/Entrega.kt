package com.nadir.apientrega.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.*

@Document(collection = "entregas")
data class Entrega(
    val idCliente: Long,
    val idProduto: Long,
    val qtdItensComprados: Integer,
    val valorUnitarioDoItem: BigDecimal,
    val valorTotal: BigDecimal) {}
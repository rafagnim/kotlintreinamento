package com.nadir.apientrega.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "entregas")
data class Entrega(@Id public val nome: String?, public val conteudo: String?) {}
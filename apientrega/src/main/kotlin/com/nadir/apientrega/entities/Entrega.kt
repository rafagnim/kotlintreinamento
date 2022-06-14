package com.nadir.apientrega.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Document(collection = "entregas")
data class Entrega(private val nome: String?, private val conteudo: String?) {
 //   @Id
 //   private val id: String? = null
//    private val data: String

//    init {
//        data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
//    }
}
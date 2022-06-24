package com.nadir.mib.models

data class Endereco (
    val id: Long? = null,
    var logradouro: String,
    var numero: String,
    var complemento: String,
    var municipio: String,
    var uf: String,
    var cep: String?
)
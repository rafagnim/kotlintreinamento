package com.nadir.mib.models

data class EnderecoRequest (
    var logradouro: String,
    var numero: String,
    var complemento: String,
    var municipio: String,
    var uf: String
    )
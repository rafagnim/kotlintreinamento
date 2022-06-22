package com.nadir.mib.models

data class Usuario(
    val id: Long? = null,
    val nome: String,
    val idade: Int,
    val enderecos: List<Endereco>? = null,
    val telefones: List<Telefone?>? = null,
    val email: String,
    val senha: String? = null,
    var isActive: Boolean
)
package com.nadir.apiclientes.responses

import com.nadir.apiclientes.entities.Endereco
import com.nadir.apiclientes.entities.Telefone

data class UsuarioResponse(

    val id: Long,
    val nome: String,
    val idade: Int,
    val enderecos: List<Endereco>? = null,
    val telefones: List<Telefone?>? = null,
    val email: String,
    var isActive: Boolean
)
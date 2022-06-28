package com.nadir.mib.responses

import com.nadir.mib.models.Endereco
import com.nadir.mib.models.TelefoneRequest

data class UsuarioResponse(

    val id: Long,
    val nome: String,
    val idade: Int,
    val enderecos: List<Endereco>? = null,
    val telefones: List<TelefoneRequest?>? = null,
    val email: String,
    var isActive: Boolean
)
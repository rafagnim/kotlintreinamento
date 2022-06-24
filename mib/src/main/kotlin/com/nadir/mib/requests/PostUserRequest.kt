package com.nadir.mib.requests

import com.nadir.mib.models.EnderecoRequest
import com.nadir.mib.models.TelefoneRequest

data class PostUserRequest (
    val nome: String,
    val idade: Int,
    var enderecos: List<EnderecoRequest>?,
    val telefones: List<TelefoneRequest?>?,
    val email: String,
    var senha: String
)
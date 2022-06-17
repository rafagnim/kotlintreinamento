package com.nadir.apicep.requests

import com.nadir.apicep.entities.EnderecoId
import java.util.*
import javax.validation.constraints.NotEmpty

data class EnderecoIdRequest (
    @field:NotEmpty(message = "Obrigatório informar a uf")
    var uf: String,

    @field:NotEmpty(message = "Obrigatório informar o município")
    var municipio: String,

    @field:NotEmpty(message = "Obrigatório informar o logradouro")
    var logradouro: String
        ) {
    fun toEnderecoEntity(endereco: EnderecoId?): EnderecoId {
        return if (endereco == null)
            EnderecoId(
                uf = this.uf.uppercase(Locale.getDefault()),
                municipio = this.municipio.uppercase(Locale.getDefault()),
                logradouro = this.logradouro.uppercase(Locale.getDefault())
            )
        else
            EnderecoId(
                uf = endereco.uf,
                municipio = endereco.municipio,
                logradouro = endereco.logradouro
            )
    }
}
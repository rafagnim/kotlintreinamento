package com.nadir.apientrega.requests

import com.nadir.apientrega.entities.Entrega
import javax.validation.constraints.NotEmpty

class EntregaRequest (
    @field:NotEmpty(message = "O nome é obrigatório.")
    val nome : String,
    @field:NotEmpty(message = "O conteudo é obrigatório.")
    val conteudo : String
        ) {

        fun toEntregaEntity(entrega: Entrega?): Entrega {
            return if (entrega == null)
                Entrega(
                    nome = this.nome,
                    conteudo = this.conteudo
                )
            else
                Entrega(
                    nome = entrega.nome,
                    conteudo = this.conteudo,
                )
        }
}
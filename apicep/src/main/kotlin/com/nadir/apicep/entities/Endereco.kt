package com.nadir.apicep.entities

import java.math.BigDecimal
import javax.persistence.*


@Entity
@Table(name = "tb_cep")
data class Endereco (

    //id composto UF + municipio + logradouro (Embeded)
    @EmbeddedId
    val enderecoId: EnderecoId,

    @Column(name = "cep")
    val cep: String

) {

}
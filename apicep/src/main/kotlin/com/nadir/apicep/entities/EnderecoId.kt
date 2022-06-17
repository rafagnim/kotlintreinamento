package com.nadir.apicep.entities

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class EnderecoId (

    @Column(name = "uf")
    var uf: String,

    @Column(name = "municipio")
    var municipio: String,

    @Column(name = "logradouro")
    var logradouro: String

        ): Serializable {

}
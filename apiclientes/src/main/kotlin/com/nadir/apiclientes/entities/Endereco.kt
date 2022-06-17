package com.nadir.apiclientes.entities

import javax.persistence.*

@Entity
@Table(name = "tb_endereco")
data class Endereco (
    @Id
    @Column(name = "endereco_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "logradouro")
    var logradouro: String,

    @Column(name = "numero")
    var numero: String,

    @Column(name = "complemento")
    var complemento: String,

    @Column(name = "municipio")
    var municipio: String,

    @Column(name = "uf")
    var uf: String,

    @Column(name = "cep")
    var cep: String?,

        ){


}
package com.nadir.apiclientes.entities

import javax.persistence.*

@Entity
@Table(name = "tb_telefone")
data class Telefone (
    @Id
    @Column(name = "telefone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "ddd")
    var ddd: Integer,

    @Column(name = "numero")
    var numero: Integer

        ) {
}
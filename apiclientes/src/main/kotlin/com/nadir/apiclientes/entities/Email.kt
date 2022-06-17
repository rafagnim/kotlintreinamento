package com.nadir.apiclientes.entities

import javax.persistence.*

@Entity
@Table(name = "tb_email")
data class Email (
    @Id
    @Column(name = "email_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "email")
    var email: String
        ){
}
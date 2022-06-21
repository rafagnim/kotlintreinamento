package br.project.users.users.entity

import br.project.users.users.entity.User
import javax.persistence.*

@Entity
@Table(name = "tb_endereco")
data class Address(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Int? = null,

        @Column(name = "ds_rua")
        val street : String,

        @Column(name = "nm_numero")
        val number : Int,

        @Column(name = "ds_cidade")
        val city : String,

        @Column(name = "ds_estado")
        val state : String,

        @ManyToOne
        @JoinColumn(name = "id", insertable = false, updatable = false)
        val user: User? = null
)

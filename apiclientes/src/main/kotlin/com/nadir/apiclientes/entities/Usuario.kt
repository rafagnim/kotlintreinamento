package com.nadir.apiclientes.entities

import java.util.stream.Stream
import javax.persistence.*

@Entity
@Table(name = "tb_usuario")
data class Usuario(
    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "nome")
    var nome: String,

    @Column(name = "idade")
    var idade: Integer,

    @OneToMany(cascade = [CascadeType.ALL])
    var enderecos: List<Endereco>? = null,

    @OneToMany(cascade = [CascadeType.ALL])
    var telefones: List<Telefone?>? = null,

    @OneToMany(cascade = [CascadeType.ALL])
    var emails: List<Email?>? = null,

    @Column(name = "senha")
    var senha: String,

    @Column(name = "isactive")
    var isActive: Boolean
){
}
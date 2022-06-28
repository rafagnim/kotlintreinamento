package com.nadir.apiclientes.entities

import com.nadir.apiclientes.responses.UsuarioResponse
import javax.persistence.*

@Entity
@Table(name = "tb_usuario")
data class Usuario(
    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "nome")
    val nome: String,

    @Column(name = "idade")
    val idade: Int,

    @OneToMany(cascade = [CascadeType.ALL])
    val enderecos: List<Endereco>? = null,

    @OneToMany(cascade = [CascadeType.ALL])
    val telefones: List<Telefone?>? = null,

    @Column(name = "email")
    val email: String,

    @Column(name = "senha")
    val senha: String? = null,

    @Column(name = "isactive")
    var isActive: Boolean
){
    fun toGetAll() : Usuario{
        return Usuario(
            nome = this.nome,
            idade = this.idade,
            enderecos =  this.enderecos,
            telefones = this.telefones,
            email = this.email,
            isActive = this.isActive
        )
    }

    fun toUserResponse() : UsuarioResponse {
        return UsuarioResponse (
            id = this.id!!,
            nome = this.nome,
            idade = this.idade,
            enderecos = this.enderecos,
            telefones = this.telefones,
            email = this.email,
            isActive = this.isActive
                )
    }
}
package com.nadir.apiclientes.requests

import com.nadir.apiclientes.entities.Endereco
import com.nadir.apiclientes.entities.Telefone
import com.nadir.apiclientes.entities.Usuario
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

data class PostUserRequest(
    @field:NotEmpty(message = "O nome é obrigatório.")
    val nome: String,
    @field:Min(value = 18, message =  "É necessário ter ao menos 18 anos para cadastrar usuário.")
    val idade: Int,
    var enderecos: List<Endereco>?,
    //    @field:NotEmpty(message = "O telefone é obrigatório.")
    val telefones: List<Telefone?>?,
    @field:NotEmpty(message = "O e-mail é obrigatório.")
    val email: String,
    @field:NotEmpty(message = "A senha é obrigatória.")
    var senha: String
){
    fun toEntity() : Usuario {
        return  Usuario(
            nome = this.nome,
            idade = this.idade,
            enderecos = this.enderecos,
            telefones = this.telefones,
            email = this.email,
            senha = this.senha,
            isActive = true
        )
    }
}
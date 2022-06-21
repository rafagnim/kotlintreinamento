package com.nadir.apiclientes.requests

import com.nadir.apiclientes.entities.Email
import com.nadir.apiclientes.entities.Endereco
import com.nadir.apiclientes.entities.Telefone
import com.nadir.apiclientes.entities.Usuario
import com.nadir.apiclientes.integration.feign.client.EnderecoClient
import com.nadir.apiclientes.integration.feign.client.EnderecoId
import java.util.stream.Collectors
import javax.validation.constraints.NotEmpty

//CLASSE NÃO MAIS UTILIZADA


//============================================

//                      REVER

class UsuarioRequest (
    @field:NotEmpty(message = "Obrigatório informar o nome")
    val nome: String,

    //@field:NotEmpty(message = "Obrigatório informar a idade")
    val idade: Int,

    @field:NotEmpty(message = "Obrigatório informar uma senha")
    val senha: String,

    var enderecos: List<Endereco>?,
    var telefones: List<Telefone?>?,
    var email: String,

    //FEIGN:
    val enderecoClient: EnderecoClient?
        ) {

    fun toUsuarioEntity(usuario: Usuario?): Usuario {
        return if (usuario == null)
            Usuario(
                nome = this.nome,
                idade = this.idade,

                enderecos = this.enderecos,
                telefones = this.telefones,
                email = this.email,
                senha = this.senha,
                isActive = true
            )
        else
            Usuario(
                id = usuario.id,
                nome = usuario.nome,
                idade = usuario.idade,
                enderecos = usuario.enderecos,
                telefones = usuario.telefones,
                email = usuario.email,
                senha = usuario.senha,
                isActive =  usuario.isActive
            )
    }
}

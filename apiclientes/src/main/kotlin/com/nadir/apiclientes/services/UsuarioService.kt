package com.nadir.apiclientes.services

import com.nadir.apiclientes.entities.Usuario
import com.nadir.apiclientes.repositories.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService (
    private val usuarioRepository: UsuarioRepository
        ) {

    fun save(usuario: Usuario): Usuario {
        return usuarioRepository.save(usuario)
    }

    fun findAll(): List<Usuario> {
        return usuarioRepository.findAll()
    }
}
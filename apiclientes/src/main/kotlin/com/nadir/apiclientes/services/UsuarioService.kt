package com.nadir.apiclientes.services

import com.nadir.apiclientes.entities.Usuario
import com.nadir.apiclientes.exceptions.BadRequestException
import com.nadir.apiclientes.exceptions.NotFoundException
import com.nadir.apiclientes.repositories.UsuarioRepository
import com.nadir.apiclientes.requests.PostUserRequest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val bCrypt: BCryptPasswordEncoder
) {
    fun save(request: PostUserRequest) {
        if (usuarioRepository.existsByEmail(request.email))
            throw BadRequestException("Email already exists.")

        request.senha = bCrypt.encode(request.senha)
        usuarioRepository.save(request.toEntity())
    }

    fun disable(id: Long) {
        val user = usuarioRepository.findById(id).orElseThrow { NotFoundException("User with id %s not exists.".format(id)) }
        user.isActive = false
        usuarioRepository.save(user)
    }

    fun enable(id: Long) {
        val user = usuarioRepository.findById(id).orElseThrow { NotFoundException("User with id %s not exists.".format(id)) }
        user.isActive = true
        usuarioRepository.save(user)
    }

    fun getAll(): List<Usuario> {
        return usuarioRepository.findAll().map { it.toGetAll() }
    }

    fun getById(id : Long): Usuario{
        return usuarioRepository.findById(id).orElseThrow { NotFoundException("User with id %s not exists.".format(id)) }
    }
}
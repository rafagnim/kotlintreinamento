package com.nadir.apiclientes.services

import com.nadir.apiclientes.entities.Usuario
import com.nadir.apiclientes.exceptions.BadRequestException
import com.nadir.apiclientes.exceptions.NotFoundException
import com.nadir.apiclientes.repositories.UsuarioRepository
import com.nadir.apiclientes.requests.PostUserRequest
import com.nadir.apiclientes.responses.UsuarioResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.security.sasl.AuthenticationException

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
        if (SecurityContextHolder.getContext().authentication.principal.toString().toLong() != 1L) throw AuthenticationException("Usuário não autorizado")
        val user = usuarioRepository.findById(id).orElseThrow { NotFoundException("User with id %s not exists.".format(id)) }
        user.isActive = false
        usuarioRepository.save(user)
    }

    fun enable(id: Long) {
        if (SecurityContextHolder.getContext().authentication.principal.toString().toLong() != 1L) throw AuthenticationException("Usuário não autorizado")
        val user = usuarioRepository.findById(id).orElseThrow { NotFoundException("User with id %s not exists.".format(id)) }
        user.isActive = true
        usuarioRepository.save(user)
    }

    fun getAll(): List<Usuario> {
        return usuarioRepository.findAll().map { it.toGetAll() }
    }

    fun getById(id : Long): UsuarioResponse {
        return usuarioRepository.findById(id).orElseThrow { NotFoundException("User with id %s not exists.".format(id)) }.toUserResponse()
    }
}
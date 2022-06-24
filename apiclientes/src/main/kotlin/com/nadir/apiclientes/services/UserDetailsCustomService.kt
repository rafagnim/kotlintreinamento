package com.nadir.apiclientes.services

import com.nadir.apiclientes.exceptions.AuthenticationException
import com.nadir.apiclientes.repositories.UsuarioRepository
import com.nadir.apiclientes.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val usuarioRepository: UsuarioRepository
) : UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val user = usuarioRepository.findById(id.toLong()).orElseThrow { AuthenticationException("Usuário não encontrado") }
        return UserCustomDetails(user)
    }
}
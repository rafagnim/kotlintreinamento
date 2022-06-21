package br.project.users.users.service

import br.project.users.users.exception.AuthenticationException
import br.project.users.users.repository.UserRepository
import br.project.users.users.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
        private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val user = userRepository.findById(id.toInt()).orElseThrow { AuthenticationException("User not found.", "999") }
        return UserCustomDetails(user)
    }
}
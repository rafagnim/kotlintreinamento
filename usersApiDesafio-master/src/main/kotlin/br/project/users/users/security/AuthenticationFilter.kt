package br.project.users.users.security

import br.project.users.users.controller.request.LoginRequest
import br.project.users.users.controller.response.LoginResponse
import br.project.users.users.exception.AuthenticationException
import br.project.users.users.repository.UserRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
        authenticationManager: AuthenticationManager,
        private val userRepository: UserRepository,
        private val jwtUtil: JwtUtil
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
            val id = userRepository.findByEmail(loginRequest.email)?.id
            val authToken = UsernamePasswordAuthenticationToken(id, loginRequest.password)
            return authenticationManager.authenticate(authToken)
        } catch (ex: Exception) {
            throw AuthenticationException("Login failed", "999")
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authResult: Authentication) {
        val id = (authResult.principal as UserCustomDetails).id
        response.addHeader("Authorization", "Bearer ${jwtUtil.generateToken(id)}")
        val loginResponse = LoginResponse("Bearer ${jwtUtil.generateToken(id)}")
        response.writer.write(Gson().toJson(loginResponse));
        response.writer.flush();
    }

}
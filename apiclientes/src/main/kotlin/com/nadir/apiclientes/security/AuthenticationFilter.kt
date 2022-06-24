package com.nadir.apiclientes.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import com.nadir.apiclientes.exceptions.AuthenticationException
import com.nadir.apiclientes.repositories.UsuarioRepository
import com.nadir.apiclientes.requests.LoginRequest
import com.nadir.apiclientes.responses.LoginResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val usuarioRepository: UsuarioRepository,
    private val jwtUtil: JwtUtil
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
            val id = usuarioRepository.findByEmail(loginRequest.email)?.id
            val authToken = UsernamePasswordAuthenticationToken(id, loginRequest.senha)
            return authenticationManager.authenticate(authToken)
        } catch (ex: Exception) {
            throw AuthenticationException("Token Inválido")
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
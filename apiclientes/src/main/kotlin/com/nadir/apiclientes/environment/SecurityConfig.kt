package com.nadir.apiclientes.environment

import com.nadir.apiclientes.repositories.UsuarioRepository
import com.nadir.apiclientes.security.AuthenticationFilter
import com.nadir.apiclientes.security.AuthorizationFilter
import com.nadir.apiclientes.security.JwtUtil
import com.nadir.apiclientes.services.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.CorsUtils
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val usuarioRepository: UsuarioRepository,
    private val jwtUtil: JwtUtil,
    private val authenticationConfiguration: AuthenticationConfiguration,
    private val userDetailsCustomService: UserDetailsCustomService
)   {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors().and().csrf().disable()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilter(AuthenticationFilter(authenticationConfiguration.authenticationManager, usuarioRepository, jwtUtil))
        http.addFilter(AuthorizationFilter(authenticationConfiguration.authenticationManager, jwtUtil, userDetailsCustomService))
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/v1/usuarios/save").permitAll()
            .anyRequest().authenticated()
        //http.exceptionHandling().defaultAuthenticationEntryPointFor(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), AntPathRequestMatcher("/users/**"))
        return http.build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
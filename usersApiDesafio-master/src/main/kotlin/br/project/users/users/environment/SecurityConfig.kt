package br.project.users.users.environment

import br.project.users.users.repository.UserRepository
import br.project.users.users.security.AuthenticationFilter
import br.project.users.users.security.AuthorizationFilter
import br.project.users.users.security.JwtUtil
import br.project.users.users.service.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig(
        private val userRepository: UserRepository,
        private val jwtUtil: JwtUtil,
        private val authenticationConfiguration: AuthenticationConfiguration,
        private val userDetailsCustomService: UserDetailsCustomService
)   {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors().and().csrf().disable()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilter(AuthenticationFilter(authenticationConfiguration.authenticationManager, userRepository, jwtUtil))
        http.addFilter(AuthorizationFilter(authenticationConfiguration.authenticationManager, jwtUtil, userDetailsCustomService))
        http.authorizeRequests()
                .anyRequest().authenticated()
        //http.exceptionHandling().defaultAuthenticationEntryPointFor(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), AntPathRequestMatcher("/users/**"))
        return http.build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}
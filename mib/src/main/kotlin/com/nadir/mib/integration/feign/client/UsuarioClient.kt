package com.nadir.mib.integration.feign.client

import com.nadir.mib.models.Usuario
import com.nadir.mib.requests.LoginRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "Apiclientes",
    url = "\${integration.apiclientes.url}"
)
interface UsuarioClient {
    @PostMapping("/login")
    fun login(@RequestBody usuario: LoginRequest): String

    @GetMapping("api/v1/usuarios")
    fun getAll(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String): List<Usuario>
}



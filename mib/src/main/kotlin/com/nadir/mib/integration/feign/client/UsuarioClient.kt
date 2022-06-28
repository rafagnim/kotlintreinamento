package com.nadir.mib.integration.feign.client

import com.nadir.mib.models.Produto
import com.nadir.mib.models.Usuario
import com.nadir.mib.requests.LoginRequest
import com.nadir.mib.requests.PostUserRequest
import com.nadir.mib.responses.UsuarioResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@FeignClient(
    name = "Apiclientes",
    url = "\${integration.apiclientes.url}"
)
interface UsuarioClient {
    @PostMapping("/login")
    fun login(@RequestBody usuario: LoginRequest): String

    @PostMapping("api/v1/usuarios/save")
    fun create(@RequestBody @Valid request : PostUserRequest)

    @GetMapping("/api/v1/usuarios/{id}")
    fun findById(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long ): UsuarioResponse

    @GetMapping("api/v1/usuarios")
    fun getAll(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String): List<Usuario>

    @PatchMapping("api/v1/usuarios/disable/{id}")
    fun disable(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long)

    @PatchMapping("api/v1/usuarios/enable/{id}")
    fun enable(@RequestHeader(value = "Authorization", required = true) authorizationHeader:String, @PathVariable id : Long)
}



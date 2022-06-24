package com.nadir.mib.integration.feign.client

import com.nadir.mib.exceptions.ForbiddenAccessException
import feign.Response
import feign.codec.ErrorDecoder



class FeignErrorDecoder: ErrorDecoder {
    override fun decode(methodKey: String?, response: Response?): Exception {


        return when (response!!.status()) {
            400 -> {
                Exception("Bad Request Through Feign")
            }
            401 -> {
                Exception("Unauthorized Request Through Feign")
            }
            403 -> {
                if (methodKey == "UsuarioClient#login(LoginRequest)") {
                    ForbiddenAccessException("Usuário ou senha inválido")
                } else if (methodKey == "CompraClient#create(String,CompraRequest)" || methodKey == "ProdutoClient#retrieveProdutos(String)") {
                    ForbiddenAccessException("Token Inválido")
                } else {
                    ForbiddenAccessException("Acesso não permitido")
                }
            }
            404 -> {
                Exception("Bad Request")
            }
            else -> {
                Exception("Common Feign Exception")
            }
        }
        //return Exception(response!!.reason())
    }

//    when (methodKey) {
//        "UsuarioClient#login(LoginRequest)" -> ForbiddenAccessException("Usuário ou senha inválido")
//        "UsuarioClient#validaToken(String)" -> ForbiddenAccessException("Token Inválido")
//        else -> ForbiddenAccessException("Acesso não permitido")
//    }
}
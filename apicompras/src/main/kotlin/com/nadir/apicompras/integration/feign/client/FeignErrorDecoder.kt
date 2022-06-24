package com.nadir.apicompras.integration.feign.client

import com.nadir.apicompras.exceptions.TokenInvalidoException
import feign.Response
import feign.codec.ErrorDecoder
import java.lang.Exception

class FeignErrorDecoder: ErrorDecoder {
    override fun decode(methodKey: String?, response: Response?): Exception {


        return when (response!!.status()) {
            400 -> {
                //log.error("Error in request went through feign client")
                //handle exception
                Exception("Bad Request Through Feign")
            }
            401 -> {
                //log.error("Error in request went through feign client")
                //handle exception
                Exception("Unauthorized Request Through Feign")
            }
            403 -> {
                //log.error("Error in request went through feign client")
                //handle exception
                TokenInvalidoException("Token Invalido")
            }
            404 -> {
                //log.error("Error in request went through feign client")
                //handle exception
                Exception("Unidentified Request Through Feign")
            }
            else -> {
                //log.error("Error in request went through feign client")
                //handle exception
                Exception("Common Feign Exception")
            }
        }
        //return Exception(response!!.reason())
    }
}
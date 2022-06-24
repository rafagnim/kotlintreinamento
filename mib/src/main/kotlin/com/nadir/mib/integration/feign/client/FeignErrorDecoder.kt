package com.nadir.mib.integration.feign.client

import com.nadir.mib.exceptions.ForbiddenAccessException
import feign.Response
import feign.codec.ErrorDecoder



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
                ForbiddenAccessException("Token Inválido")
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
package com.nadir.apicompras.integration.feign.client

import feign.Response
import feign.codec.ErrorDecoder
import java.lang.Exception

class FeignErrorDecoder: ErrorDecoder {
    override fun decode(methodKey: String?, response: Response?): Exception {
        return Exception(response!!.reason())
    }
}
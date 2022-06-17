package com.nadir.apiclientes.integration.feign.client

import feign.Response
import feign.codec.ErrorDecoder


class FeignErrorDecoder: ErrorDecoder {
    override fun decode(methodKey: String?, response: Response?): Exception {
        return Exception(response!!.reason())
    }
}
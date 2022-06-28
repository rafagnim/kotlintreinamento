package com.nadir.mib.integration.feign.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nadir.mib.exceptions.ForbiddenAccessException
import com.nadir.mib.exceptions.IdadeInvalidaException
import com.nadir.mib.exceptions.MessageMapper
import com.nadir.mib.exceptions.RequisicaoInvalidaException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import org.apache.commons.io.IOUtils


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpServerErrorException

import java.io.IOException
import java.lang.Exception

class FeignErrorDecoder: ErrorDecoder {
    private val log: Logger = LoggerFactory.getLogger(FeignErrorDecoder::class.java)

    override fun decode(methodKey: String?, response: Response): Exception? {
        val responseHeaders = HttpHeaders()
        response.headers().entries.forEach { entry -> responseHeaders[entry.key] = entry.value.toList() }
        val statusCode = HttpStatus.valueOf(response.status())
        val statusText = response.reason()

        if (response.body() != null) {
            val responseBody: ByteArray = try {
                IOUtils.toByteArray(response.body().asInputStream())

            } catch (e: IOException) {
                throw RuntimeException("Failed to process response body.", e)
            }
            log.error("Request: ${response.request().httpMethod()} ${response.request().url()}")
            log.error("Request Header Authorization: ${response.request().headers()["Authorization"]}")
            log.error("Request body: ${response.request().body()?.decodeToString()}")
            log.error("Response status: ${response.status()}")
            log.error("Response body: ${responseBody.decodeToString()}")
            if (response.status() in 400..499) {
                val mapper = jacksonObjectMapper()
                val messageMapper = mapper.readValue<MessageMapper>(responseBody)
                return RequisicaoInvalidaException(messageMapper.getMessageClient(), response.status())
            }
            return if (response.status() in 500..599) {
                HttpServerErrorException(statusCode, statusText, responseHeaders, responseBody, null)
            } else FeignException.errorStatus(methodKey, response)
        }

            return when (response!!.status()) {
            400 -> {
                Exception("Bad Request Through Feign")
            }
            401 -> {
                Exception("Unauthorized Request Through Feign")
            }
            403 -> {
                if (methodKey == "UsuarioClient#login(LoginRequest)") {
                    ForbiddenAccessException("Usuário ou senha inválido", 403)
                } else if (methodKey == "CompraClient#create(String,CompraRequest)" || methodKey == "ProdutoClient#retrieveProdutos(String)") {
                    ForbiddenAccessException("Token Inválido", 403)
                } else {
                    ForbiddenAccessException("Acesso não permitido", 403)
                }
            }
            404 -> {
                Exception("Bad Request")
            }
            422 -> {
                IdadeInvalidaException("Não é permitido realizar cadastro para menor de 18 anos", 422)
            }
            else -> {
                Exception("Common Feign Exception")
            }
        }
    }
}
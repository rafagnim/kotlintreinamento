package com.nadir.mib.exceptions

import com.nadir.mib.responses.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(ForbiddenAccessException::class)
    fun handlerForbiddenAccessException(ex: ForbiddenAccessException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            " ${ex.message}"
        )
        val status: HttpStatus = HttpStatus.valueOf(ex.status)
        return ResponseEntity(error, status)
    }

    @ExceptionHandler(EstoqueInsuficenteException::class)
    fun handlerMethodEstoqueInsuficenteException(ex: EstoqueInsuficenteException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            " ${ex.message}"
        )
        val status: HttpStatus = HttpStatus.valueOf(ex.status)
        return ResponseEntity(error, status)
    }

    @ExceptionHandler(RequisicaoInvalidaException::class)
    fun handlerRequisicaoInvalidaException(ex: RequisicaoInvalidaException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            " ${ex.message}"
        )
        val status: HttpStatus = HttpStatus.valueOf(ex.status)
        return ResponseEntity(error, status)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handlerHttpMessageNotReadableException(ex: HttpMessageNotReadableException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            " Verifique o preencimento do formulário conforme orientação a seguir: ${ex.message}"
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}
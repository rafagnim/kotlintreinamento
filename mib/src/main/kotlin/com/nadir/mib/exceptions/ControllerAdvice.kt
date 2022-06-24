package com.nadir.mib.exceptions

import com.nadir.mib.responses.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(ForbiddenAccessException::class)
    fun handlerForbiddenAccessException(ex: ForbiddenAccessException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            "Token Inválido"
        )
        return ResponseEntity(error, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(EstoqueInsuficenteException::class)
    fun handlerMethodEstoqueInsuficenteException(ex: EstoqueInsuficenteException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            " ${ex.message}"
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}
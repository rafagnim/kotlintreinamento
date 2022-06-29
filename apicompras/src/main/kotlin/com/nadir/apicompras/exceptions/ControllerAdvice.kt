package com.nadir.apicompras.exceptions

import com.nadir.apicompras.reponses.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(EstoqueInsuficenteException::class)
    fun handlerMethodArgumentNotValidException(ex: EstoqueInsuficenteException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            ex.message
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(TokenInvalidoException::class)
    fun handlerMethodTokenInvalidoException(ex: TokenInvalidoException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            ex.message
        )
        return ResponseEntity(error, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handlerEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            ex.message
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
}
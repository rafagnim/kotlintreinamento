package com.nadir.apiclientes.exceptions

import com.nadir.apiclientes.responses.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.sql.SQLException



@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            ex.message
        )
        return ResponseEntity(error, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handlerMethodArgumentNotValidException(ex: BadRequestException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            ex.message
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handlerMethodArgumentNotValidException(ex: NotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            ex.message
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(AuthenticationException::class)
    fun handlerAuthenticationException(ex: NotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            ex.message
        )
        return ResponseEntity(error, HttpStatus.FORBIDDEN)
    }
}
package com.jfb.mercadolivro.exceptions

import com.jfb.mercadolivro.controllers.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.lang.Exception

@ControllerAdvice
class MercadoLivroExceptionHandler {

  @ExceptionHandler(NotFoundException::class)
  fun handlerNotFoundException(e: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
    val error = ErrorResponse(
      HttpStatus.NOT_FOUND.value(),
      e.message,
      e.errorCode,
      null
    )
    return ResponseEntity(error, HttpStatus.NOT_FOUND)
  }

  @ExceptionHandler(BadRequestException::class)
  fun handlerBadRequestException(e: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
    val error = ErrorResponse(
      HttpStatus.BAD_REQUEST.value(),
      e.message,
      e.errorCode,
      null
    )
    return ResponseEntity(error, HttpStatus.BAD_REQUEST)
  }
}
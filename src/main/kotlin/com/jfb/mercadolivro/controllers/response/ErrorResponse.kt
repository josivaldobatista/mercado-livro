package com.jfb.mercadolivro.controllers.response

data class ErrorResponse(
  var status: Int,
  var message: String?,
  var internalCode: String,
  var errors: List<FieldErrorResponse>?
)
package com.jfb.mercadolivro.controllers.request

import com.jfb.mercadolivro.validations.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CustomerRequest(
  @field:NotEmpty(message = "Nome deve ser informado")
  var nome: String,
  @EmailAvailable
  @field:Email(message = "Email deve em um formato válido")
  var email: String
)
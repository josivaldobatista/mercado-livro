package com.jfb.mercadolivro.controllers.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CustomerUpdateRequest(
  @field:NotEmpty(message = "Nome deve ser informado")
  val nome: String,
  @field:Email(message = "Email deve em um formato válido")
  val email: String
)
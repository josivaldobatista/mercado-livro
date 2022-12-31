package com.jfb.mercadolivro.controllers.response

import com.jfb.mercadolivro.models.enums.CustomerStatus

data class CustomerResponse(
  var id: Int? = null,
  var nome: String,
  var email: String,
  var status: CustomerStatus
)

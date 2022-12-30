package com.jfb.mercadolivro.extensions

import com.jfb.mercadolivro.controllers.request.CustomerRequest
import com.jfb.mercadolivro.controllers.request.CustomerUpdateRequest
import com.jfb.mercadolivro.models.Customer

fun CustomerRequest.toCustomer() = Customer(
  nome = this.nome,
  email = this.email
)

fun CustomerUpdateRequest.toCustomer(id: String):  Customer {
  return Customer(
    id = id,
    nome = this.nome,
    email = this.email
  )
}
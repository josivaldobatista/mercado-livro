package com.jfb.mercadolivro.extensions

import com.jfb.mercadolivro.controllers.response.CustomerResponse
import com.jfb.mercadolivro.controllers.request.BookRequest
import com.jfb.mercadolivro.controllers.request.BookUpdateRequest
import com.jfb.mercadolivro.controllers.request.CustomerRequest
import com.jfb.mercadolivro.controllers.request.CustomerUpdateRequest
import com.jfb.mercadolivro.controllers.response.BookResponse
import com.jfb.mercadolivro.models.Book
import com.jfb.mercadolivro.models.Customer
import com.jfb.mercadolivro.models.enums.BookStatus
import com.jfb.mercadolivro.models.enums.CustomerStatus

fun CustomerRequest.toCustomer() = Customer(
  nome = this.nome,
  email = this.email,
  status = CustomerStatus.ATIVO
)

fun CustomerUpdateRequest.toCustomer(previousValue: Customer):  Customer {
  return Customer(
    id = previousValue.id,
    nome = this.nome,
    email = this.email,
    status = previousValue.status
  )
}

fun BookRequest.toBook(customer: Customer) = Book(
  name = this.name,
  price = this.price,
  status = BookStatus.ATIVO,
  customer = customer
)

fun BookUpdateRequest.toBook(previousValue: Book) = Book(
  id = previousValue.id,
  name = this.name ?: previousValue.name, // Elvis operator -> "?:"
  price = this.price ?: previousValue.price, // Se não for nulo retorna no "this.price" mas se for nulo retorna o "previousValue.price"
  status = previousValue.status,
  customer = previousValue.customer
)

fun Customer.toResponse(): CustomerResponse {
  return CustomerResponse(
    id = this.id,
    nome = this.nome,
    email = this.email,
    status = this.status
  )
}

fun  Book.toResponse(): BookResponse {
  return BookResponse(
    id = this.id,
    name = this.name,
    price = this.price,
    customer = this.customer,
    status = this.status
  )
}
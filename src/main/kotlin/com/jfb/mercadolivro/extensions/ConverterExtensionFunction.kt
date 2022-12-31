package com.jfb.mercadolivro.extensions

import com.jfb.mercadolivro.controllers.request.BookRequest
import com.jfb.mercadolivro.controllers.request.BookUpdateRequest
import com.jfb.mercadolivro.controllers.request.CustomerRequest
import com.jfb.mercadolivro.controllers.request.CustomerUpdateRequest
import com.jfb.mercadolivro.models.Book
import com.jfb.mercadolivro.models.Customer
import com.jfb.mercadolivro.models.enums.BookStatus

fun CustomerRequest.toCustomer() = Customer(
  nome = this.nome,
  email = this.email
)

fun CustomerUpdateRequest.toCustomer(id: Int):  Customer {
  return Customer(
    id = id,
    nome = this.nome,
    email = this.email
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
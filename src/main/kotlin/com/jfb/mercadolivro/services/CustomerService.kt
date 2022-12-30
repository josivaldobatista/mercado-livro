package com.jfb.mercadolivro.services

import com.jfb.mercadolivro.models.Customer
import org.springframework.stereotype.Service

@Service
class CustomerService {

  val customers = mutableListOf<Customer>()

  fun create(
    customer: Customer
  ) {
    val id = if (customers.isEmpty()) {
      1
    } else {
      customers.last().id!!.toInt() + 1
    }.toString()

    customer.id = id

    customers.add(customer)
  }

  fun findAll(
    nome: String?
  ): List<Customer> {
    nome?.let {
      return customers.filter { it.nome.contains(nome, true) }
    }
    return customers
  }

  fun findCustomerById(
    id: String
  ): Customer {
    return customers.filter { it.id == id }.first()
  }

  fun update(customer: Customer) {
    customers.filter { it.id == customer.id }.first().let {
      it.nome = customer.nome
      it.email = customer.email
    }
  }

  fun delete(id: String) {
    customers.removeIf { it.id == id }
  }
}
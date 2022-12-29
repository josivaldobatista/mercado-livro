package com.jfb.mercadolivro.controllers

import com.jfb.mercadolivro.controllers.request.CustomerRequest
import com.jfb.mercadolivro.controllers.request.CustomerUpdateRequest
import com.jfb.mercadolivro.models.Customer
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController {

  val customers = mutableListOf<Customer>()

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createCustomer(
    @RequestBody customer: CustomerRequest
  ) {
    val id = if (customers.isEmpty()) {
      "1"
    } else {
      customers.last().id.toInt() + 1
    }.toString()
    customers.add(Customer(id, customer.nome, customer.email))
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun getAll(): MutableList<Customer> {
    return customers
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  fun getCustomer(
    @PathVariable("id") id: String
  ): Customer {
    return customers.filter { it.id == id }.first()
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun update(
    @PathVariable("id") id: String,
    @RequestBody customer: CustomerUpdateRequest
  ) {
    customers.filter { it.id == id }.first().let {
      it.nome = customer.nome
      it.email = customer.email
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun delete(
    @PathVariable("id") id: String
  ) {
    customers.removeIf { it.id == id }
  }

}
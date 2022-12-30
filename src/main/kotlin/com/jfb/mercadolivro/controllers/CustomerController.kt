package com.jfb.mercadolivro.controllers

import com.jfb.mercadolivro.controllers.request.CustomerRequest
import com.jfb.mercadolivro.controllers.request.CustomerUpdateRequest
import com.jfb.mercadolivro.extensions.toCustomer
import com.jfb.mercadolivro.models.Customer
import com.jfb.mercadolivro.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(
  val customerService: CustomerService
) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun create(@RequestBody customer: CustomerRequest) {
    customerService.create(customer.toCustomer())
  }

  @GetMapping
  fun findAll(
    @RequestParam nome: String?
  ): List<Customer> {
    return customerService.findAll(nome)
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  fun findCustomer(
    @PathVariable("id") id: String
  ): Customer {
    return customerService.findCustomerById(id)
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun update(
    @PathVariable("id") id: String,
    @RequestBody customer: CustomerUpdateRequest) {
    customerService.update(customer.toCustomer(id))
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun delete(
    @PathVariable("id") id: String
  ) {
    return customerService.delete(id)
  }
}

package com.jfb.mercadolivro.controllers

import com.jfb.mercadolivro.controllers.request.CustomerRequest
import com.jfb.mercadolivro.controllers.request.CustomerUpdateRequest
import com.jfb.mercadolivro.controllers.response.CustomerResponse
import com.jfb.mercadolivro.extensions.toCustomer
import com.jfb.mercadolivro.extensions.toResponse
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
  fun findAll(@RequestParam nome: String?): List<CustomerResponse> {
    return customerService.findAll(nome)
      .map { it.toResponse() }
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  fun findCustomerById(@PathVariable("id") id: Int): CustomerResponse {
    return customerService.findById(id).toResponse()
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun update(
    @PathVariable("id") id: Int,
    @RequestBody customer: CustomerUpdateRequest) {
    val customerSaved = customerService.findById(id)
    customerService.update(customer.toCustomer(customerSaved))
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun delete(@PathVariable("id") id: Int) {
    return customerService.delete(id)
  }
}

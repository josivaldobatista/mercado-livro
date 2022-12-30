package com.jfb.mercadolivro.services

import com.jfb.mercadolivro.models.Customer
import com.jfb.mercadolivro.repositories.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
  val customerRepository: CustomerRepository
) {

  fun create(customer: Customer) {
    customerRepository.save(customer)
  }

  fun findAll(nome: String?): List<Customer> {
    nome?.let {
      return customerRepository.findByNomeContaining(nome)
    }
    return customerRepository.findAll()
  }

  fun findCustomerById(id: Int): Customer {
    return customerRepository.findById(id).orElseThrow()
  }

  fun update(customer: Customer) {
    if (!customerRepository.existsById(customer.id!!)) {
      throw Exception()
    }

    customerRepository.save(customer)
  }

  fun delete(id: Int) {
    if (!customerRepository.existsById(id)) {
      throw Exception()
    }

    customerRepository.deleteById(id)
  }
}
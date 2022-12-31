package com.jfb.mercadolivro.repositories

import com.jfb.mercadolivro.models.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Int> {
  fun findByNomeContaining(nome: String): List<Customer>
  fun existsByEmail(email: String): Boolean
}
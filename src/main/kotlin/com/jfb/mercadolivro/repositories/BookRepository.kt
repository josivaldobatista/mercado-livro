package com.jfb.mercadolivro.repositories

import com.jfb.mercadolivro.models.Book
import com.jfb.mercadolivro.models.Customer
import com.jfb.mercadolivro.models.enums.BookStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book, Int> {

  fun findByStatus(status: BookStatus, pageable: Pageable): Page<Book>
  fun findByCustomer(customer: Customer): List<Book>
}
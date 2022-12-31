package com.jfb.mercadolivro.repositories

import com.jfb.mercadolivro.models.Book
import com.jfb.mercadolivro.models.enums.BookStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book, Int> {

  fun findByStatus(ativo: BookStatus): List<Book>
}
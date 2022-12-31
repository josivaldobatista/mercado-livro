package com.jfb.mercadolivro.services

import com.jfb.mercadolivro.models.Book
import com.jfb.mercadolivro.models.enums.BookStatus
import com.jfb.mercadolivro.repositories.BookRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping

@Service
class BookService(
  val bookRepository: BookRepository
) {
  fun create(book: Book) {
    bookRepository.save(book)
  }

  fun findAll(): List<Book> {
    return bookRepository.findAll()
  }

  fun findActive(): List<Book> {
    return bookRepository.findByStatus(BookStatus.ATIVO)
  }

  fun findById(id: Int): Book {
    return bookRepository.findById(id).orElseThrow()
  }

  fun delete(id: Int) {
    val book = findById(id)
    book.status = BookStatus.CANCELADO
    update(book)
  }

  fun update(book: Book) {
    bookRepository.save(book)
  }

}

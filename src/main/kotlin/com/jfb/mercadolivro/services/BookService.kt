package com.jfb.mercadolivro.services

import com.jfb.mercadolivro.models.Book
import com.jfb.mercadolivro.models.Customer
import com.jfb.mercadolivro.models.enums.BookStatus
import com.jfb.mercadolivro.repositories.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
  val bookRepository: BookRepository
) {
  fun create(book: Book) {
    bookRepository.save(book)
  }

  fun findAll(pageable: Pageable): Page<Book> {
    return bookRepository.findAll(pageable)
  }

  fun findActive(pageable: Pageable): Page<Book> {
    return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
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

  fun deleteByCustomer(customer: Customer) {
    val books = bookRepository.findByCustomer(customer)
    for (book in books) {
      book.status = BookStatus.DELETADO
    }
    bookRepository.saveAll(books)
  }

}

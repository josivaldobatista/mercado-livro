package com.jfb.mercadolivro.controllers

import com.jfb.mercadolivro.controllers.request.BookRequest
import com.jfb.mercadolivro.controllers.request.BookUpdateRequest
import com.jfb.mercadolivro.extensions.toBook
import com.jfb.mercadolivro.models.Book
import com.jfb.mercadolivro.services.BookService
import com.jfb.mercadolivro.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(
  val bookService: BookService,
  val customerService: CustomerService
) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun create(@RequestBody request: BookRequest) {
    val customer = customerService.findById(request.customerId)
    bookService.create(request.toBook(customer))
  }

  @GetMapping
  fun findAll(): List<Book> {
    return bookService.findAll()
  }

  @GetMapping("/active")
  fun findActive(): List<Book> = bookService.findActive()

  @GetMapping("/{id}")
  fun findById(@PathVariable id: Int): Book {
    return bookService.findById(id)
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun delete(@PathVariable id: Int) {
    bookService.delete(id)
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun update(@PathVariable id: Int, @RequestBody book: BookUpdateRequest) {
    val bookSaved = bookService.findById(id)
    bookService.update(book.toBook(bookSaved))
  }

}
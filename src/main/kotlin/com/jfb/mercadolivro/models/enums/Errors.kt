package com.jfb.mercadolivro.models.enums

enum class Errors(
  val code: String,
  val message: String) {
  PYTE001("PYTE001", "Invalid Request"),
  PYTE101("PYTE101", "Book %s not exists"),
  PYTE102("PYTE102", "Cannot update a book with status %s"),
  PYTE202("PYTE202", "Customer %s not exists")
}
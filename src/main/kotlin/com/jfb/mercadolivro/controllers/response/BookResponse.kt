package com.jfb.mercadolivro.controllers.response

import com.jfb.mercadolivro.models.Customer
import com.jfb.mercadolivro.models.enums.BookStatus
import java.math.BigDecimal

data class BookResponse(
  var id: Int? = null,
  var name: String,
  var price: BigDecimal,
  var customer: Customer? = null,
  var status: BookStatus? = null
)

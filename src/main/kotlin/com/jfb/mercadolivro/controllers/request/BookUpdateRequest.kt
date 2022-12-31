package com.jfb.mercadolivro.controllers.request

import java.math.BigDecimal

data class BookUpdateRequest(
  var name: String?,
  var price: BigDecimal?
)

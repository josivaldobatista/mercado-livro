package com.jfb.mercadolivro.models

import com.jfb.mercadolivro.models.enums.BookStatus
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "book")
data class Book(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int? = null,
  @Column
  var name: String,
  @Column
  var price: BigDecimal,
  @Column
  @Enumerated(EnumType.STRING)
  var status: BookStatus,
  @ManyToOne
  @JoinColumn(name = "customer_id")
  var customer: Customer? = null
)
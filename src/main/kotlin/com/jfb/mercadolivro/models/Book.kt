package com.jfb.mercadolivro.models

import com.jfb.mercadolivro.exceptions.BadRequestException
import com.jfb.mercadolivro.models.enums.BookStatus
import com.jfb.mercadolivro.models.enums.Errors
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

  @ManyToOne
  @JoinColumn(name = "customer_id")
  var customer: Customer? = null
) {

  @Column
  @Enumerated(EnumType.STRING)
  var status: BookStatus? = null
    set(value) {
      if (field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
        throw BadRequestException(Errors.PYTE102.message.format(field), Errors.PYTE102.code)
      }
      field = value
    }

  // Abaixo está um exemplo de criação de construtor
  constructor(
    id: Int? = null,
    name: String,
    price: BigDecimal,
    customer: Customer? = null,
    status: BookStatus?
  ) : this(id, name, price, customer) {
    this.status = status
  }
}

package com.jfb.mercadolivro.models

import com.jfb.mercadolivro.models.enums.CustomerStatus
import javax.persistence.*

@Entity
@Table(name = "customer")
data class Customer(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int? = null,

  @Column
  var nome: String,

  @Column
  var email: String,

  @Column
  @Enumerated(EnumType.STRING)
  var status: CustomerStatus
)
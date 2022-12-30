package com.jfb.mercadolivro.models

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
  var email: String
)
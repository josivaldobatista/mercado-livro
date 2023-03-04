package com.jfb.mercadolivro.controllers

import com.jfb.mercadolivro.controllers.request.CustomerRequest
import com.jfb.mercadolivro.controllers.request.CustomerUpdateRequest
import com.jfb.mercadolivro.controllers.response.CustomerResponse
import com.jfb.mercadolivro.extensions.toCustomer
import com.jfb.mercadolivro.extensions.toResponse
import com.jfb.mercadolivro.infrastructure.sre.annotations.EnableSreMetrics
import com.jfb.mercadolivro.infrastructure.sre.annotations.SreCounter
import com.jfb.mercadolivro.infrastructure.sre.annotations.SreHistogram
import com.jfb.mercadolivro.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@EnableSreMetrics
@RequestMapping("/customers")
class CustomerController(
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SreCounter(metricName = "create_customer")
    @SreHistogram(
        metricName = "create_customer_histogram",
        buckets = [
            1000.0,
            1500.0,
            2000.0,
            2100.0,
            2200.0,
            2300.0,
            2400.0,
            2800.0,
            3000.0,
            3200.0,
            3500.0,
            3800.0,
            4000.0,
            4200.0,
            4500.0,
            4800.0,
            5000.0,
        ]
    )
    fun create(@RequestBody @Valid customer: CustomerRequest) {
        customerService.create(customer.toCustomer())
    }

    @GetMapping
    @SreCounter(metricName = "create_customer")
    @SreHistogram(
      metricName = "create_customer_histogram",
      buckets = [
        1000.0,
        1500.0,
        2000.0,
        2100.0,
        2200.0,
        2300.0,
        2400.0,
        2800.0,
        3000.0,
        3200.0,
        3500.0,
        3800.0,
        4000.0,
        4200.0,
        4500.0,
        4800.0,
        5000.0,
      ]
    )
    fun findAll(@RequestParam nome: String?): List<CustomerResponse> {
        return customerService.findAll(nome)
            .map { it.toResponse() }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @SreCounter(metricName = "create_customer")
    @SreHistogram(
      metricName = "create_customer_histogram",
      buckets = [
        1000.0,
        1500.0,
        2000.0,
        2100.0,
        2200.0,
        2300.0,
        2400.0,
        2800.0,
        3000.0,
        3200.0,
        3500.0,
        3800.0,
        4000.0,
        4200.0,
        4500.0,
        4800.0,
        5000.0,
      ]
    )
    fun findCustomerById(@PathVariable("id") id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SreCounter(metricName = "create_customer")
    @SreHistogram(
      metricName = "create_customer_histogram",
      buckets = [
        1000.0,
        1500.0,
        2000.0,
        2100.0,
        2200.0,
        2300.0,
        2400.0,
        2800.0,
        3000.0,
        3200.0,
        3500.0,
        3800.0,
        4000.0,
        4200.0,
        4500.0,
        4800.0,
        5000.0,
      ]
    )
    fun update(
        @PathVariable("id") id: Int,
        @RequestBody @Valid customer: CustomerUpdateRequest
    ) {
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomer(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SreCounter(metricName = "create_customer")
    @SreHistogram(
      metricName = "create_customer_histogram",
      buckets = [
        1000.0,
        1500.0,
        2000.0,
        2100.0,
        2200.0,
        2300.0,
        2400.0,
        2800.0,
        3000.0,
        3200.0,
        3500.0,
        3800.0,
        4000.0,
        4200.0,
        4500.0,
        4800.0,
        5000.0,
      ]
    )
    fun delete(@PathVariable("id") id: Int) {
        return customerService.delete(id)
    }
}

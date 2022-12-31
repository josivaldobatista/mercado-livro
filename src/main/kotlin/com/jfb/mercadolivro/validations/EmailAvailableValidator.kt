package com.jfb.mercadolivro.validations

import com.jfb.mercadolivro.services.CustomerService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvailableValidator(
  private val customerService: CustomerService
): ConstraintValidator<EmailAvailable, String> {

  override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
    if (value.isNullOrEmpty()) {
      return false
    }
    return customerService.emailAvailable(value)
  }

}

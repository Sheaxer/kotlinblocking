package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.pojo.Account
import fei.stuba.gono.kotlin.blocking.validation.annotations.ValidAccount
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class AccountValidator : ConstraintValidator<ValidAccount, Account> {
    override fun isValid(p0: Account?, p1: ConstraintValidatorContext?): Boolean {
        if (p0 != null) {
            if (p0.iban.isNullOrEmpty()) {
                if (p0.bic.isNullOrEmpty() || p0.localAccountNumber.isNullOrEmpty())
                    return false
                return true
            }
        }
        return false
    }
}
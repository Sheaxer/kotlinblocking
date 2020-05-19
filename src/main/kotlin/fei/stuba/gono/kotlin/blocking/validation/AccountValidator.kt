package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.blocking.validation.annotations.ValidAccount
import fei.stuba.gono.kotlin.pojo.AccountNO
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/***
 * Class implementing validation of ValidAccount annotation.
 * Valid AccountNO must contain either IBAN with optional BIC or local account number.
 * Trieda implementujúca validáciu objektu triedy AccountNO s ValidAccount anotáciou.
 * Platný objekt AccoutNO musí obsahovať buď - IBAN a volitelný BIC - alebo lokálne číslo účtu.
 * @see ValidAccount
 */
class AccountValidator : ConstraintValidator<ValidAccount, AccountNO> {
    override fun isValid(p0: AccountNO?, p1: ConstraintValidatorContext?): Boolean {
        if (p0 != null)
        {
            if (p0.iban.isNullOrEmpty()) {
                if (p0.localAccountNumber.isNullOrEmpty())
                    return false
                return true
            }
            return true
        }
        return true
    }
}
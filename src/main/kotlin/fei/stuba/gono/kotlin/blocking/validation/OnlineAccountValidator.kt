package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.blocking.services.AccountService
import fei.stuba.gono.kotlin.blocking.validation.annotations.OnlineAccount
import fei.stuba.gono.kotlin.pojo.Account
import org.springframework.beans.factory.annotation.Autowired
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class OnlineAccountValidator : ConstraintValidator<OnlineAccount,Account> {

    @Autowired
    val accountService : AccountService? = null

    override fun isValid(p0: Account?, p1: ConstraintValidatorContext?): Boolean {
        if(p0 == null)
            return true
        var acc : Account? = null
        if(p0.iban != null)
            acc = accountService?.getAccountByIban(p0.iban!!)
        else if (p0.localAccountNumber != null)
            acc = accountService?.getAccountByLocalNumber(p0.localAccountNumber!!)

        if(acc == null)
            return false
        return acc.isActive
    }
}
package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.blocking.services.AccountService
import fei.stuba.gono.kotlin.blocking.validation.annotations.OnlineAccount
import fei.stuba.gono.kotlin.pojo.Account
import fei.stuba.gono.kotlin.pojo.AccountNO
import org.springframework.beans.factory.annotation.Autowired
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class OnlineAccountValidator : ConstraintValidator<OnlineAccount,AccountNO> {

    @Autowired
    val accountService : AccountService? = null

    override fun isValid(p0: AccountNO?, p1: ConstraintValidatorContext?): Boolean {
        if(p0 == null)
            return true
        var acc : Account? = null
        if(p0.iban != null) {
            acc = accountService?.getAccountByIban(p0.iban!!)?.orElse(null)
        } else if (p0.localAccountNumber != null) {
            acc = accountService?.getAccountByLocalNumber(p0.localAccountNumber!!)?.orElse(null)
        }

        if(acc == null) {
            return false
        }
        return acc.isActive ?: false
    }
}
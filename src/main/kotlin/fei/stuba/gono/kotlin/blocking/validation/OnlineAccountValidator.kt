package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.blocking.services.AccountService
import fei.stuba.gono.kotlin.blocking.validation.annotations.OnlineAccount
import fei.stuba.gono.kotlin.pojo.Account
import fei.stuba.gono.kotlin.pojo.AccountNO
import org.springframework.beans.factory.annotation.Autowired
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/***
 * Class implementing validation of OnlineAccount annotation. AccountNo must represent a saved account
 * that is online.
 */
class OnlineAccountValidator : ConstraintValidator<OnlineAccount,AccountNO> {

    @Autowired
    val accountService : AccountService? = null

    override fun isValid(p0: AccountNO?, p1: ConstraintValidatorContext?): Boolean {
        if(p0 == null)
            return true
        val acc : Account? = when {
            p0.iban != null -> {
                accountService?.getAccountByIban(p0.iban!!)?.orElse(null)
            }
            p0.localAccountNumber != null -> {
                accountService?.getAccountByLocalNumber(p0.localAccountNumber!!)?.orElse(null)
            }
            else -> return true
        }
                ?: return false

        return acc?.isActive ?: false
    }
}
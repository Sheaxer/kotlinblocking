package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.blocking.services.AccountService
import fei.stuba.gono.kotlin.blocking.validation.annotations.OnlineAccount
import fei.stuba.gono.kotlin.pojo.Account
import fei.stuba.gono.kotlin.pojo.AccountNO
import org.springframework.beans.factory.annotation.Autowired
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/***
 * Class implementing validation of AccountNO object with OnlineAccount annotation.
 * AccountNo must represent a saved account
 * that is online e.g. AccountNO must have IBAN or local account number representing Account that is retrieved
 * via AccountService instance and the property isActive of this Account is true.
 * Trieda implementujúca validáciu objektov triedy AccountNO s OnlineAccount anotáciou.
 * Platný objekt musí reprezentovať uložený, aktívny účet, t.j. objekt musí obsahovať buď IBAN alebo
 * lokálne číslo účtu pomocou ktorého inštancia služby AccountService vie získať inštanciu triedy Account,
 * ktorá má premennú isActive nastavenú na true.
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
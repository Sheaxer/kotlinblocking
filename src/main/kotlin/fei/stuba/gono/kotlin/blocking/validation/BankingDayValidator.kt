package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.blocking.validation.annotations.BankingDay
import java.util.*
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
/***
 * Class implementing the validation of BankingDay annotation of Date class. Date must not be
 * on a weekend.
 * Trieda implementujúca validáciu objetku triedy Date anotovanú BankingDay. Platný
 * dátum nesmie byť víkend.
 * @see BankingDay
 */
class BankingDayValidator : ConstraintValidator<BankingDay, Date> {
    override fun isValid(p0: Date?, p1: ConstraintValidatorContext?): Boolean {
        val c = Calendar.getInstance()
        c.time = p0
        val day = c.get(Calendar.DAY_OF_WEEK)
        if(day == Calendar.SATURDAY || day == Calendar.SUNDAY)
            return false
        return true
    }
}
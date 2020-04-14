package fei.stuba.gono.kotlin.validation

import fei.stuba.gono.kotlin.validation.annotations.BankingDay
import java.util.*
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

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
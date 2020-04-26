package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.blocking.validation.annotations.DaysBeforeDate
import org.springframework.beans.factory.annotation.Value
import java.util.*
import java.util.concurrent.TimeUnit
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
/***
 * Class implementing validation for DaysBeforeDate annotation. Date must be at least x number of in the future
 * from the moment of validation.
 * @see DaysBeforeDate
 */
class DaysBeforeDateValidator: ConstraintValidator<DaysBeforeDate, Date> {

    var today: Date = Date()

    /***
     * Minimal number of days - days property of DaysBeforeDate annotation or if not used
     * reportedOverlimitTransaction.daysBefore property, default 3.
     */
    @Value("\${reportedOverlimitTransaction.daysBefore:3}")
    var cDays:Long = 3L

    override fun isValid(p0: Date?, p1: ConstraintValidatorContext?): Boolean {
        if(p0 == null)
            return false
        val diff = p0.time - this.today.time
        if(diff < 0)
            return true
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) >= this.cDays
    }

    override fun initialize(constraintAnnotation: DaysBeforeDate?) {
        this.today = Date()
        this.cDays = if(constraintAnnotation?.days == null || constraintAnnotation.days == 0L) this.cDays else
            constraintAnnotation.days
    }
}
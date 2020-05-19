package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.blocking.validation.annotations.DaysBeforeDate
import jdk.nashorn.internal.runtime.regexp.joni.Config.log
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import java.util.*
import java.util.concurrent.TimeUnit
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


/***
 * Class implementing validation Date object with DaysBeforeDate annotation.
 * Date must be at least x number of in the future
 * from the moment of validation.
 * Trieda implementujúca validáciu objektu triedy Date s DaysBeforeDate anotáciou.
 * Dátum musí byť aspoň x počet dní v budúcnosti, než čas validácie.
 * @see DaysBeforeDate
 */
@Slf4j
class DaysBeforeDateValidator: ConstraintValidator<DaysBeforeDate, Date> {

    var today: Date = Date()

    /***
     * Minimal number of days - days property of DaysBeforeDate annotation or if not used
     * reportedOverlimitTransaction.daysBefore property, default 3.
     * Minimálny počet dní - days premenná DaysBeforeDate anotácie alebo ak nie je zadaná,
     * tak reportedOverlimitTransaction.daysBefore property, alebo predvolená hodnota 3.
     */
    @Value("\${reportedOverlimitTransaction.daysBefore:3}")
    var cDays:Long = 3L

    override fun isValid(p0: Date?, p1: ConstraintValidatorContext?): Boolean {
        if(p0 == null)
            return true

        val diff = p0.time - this.today.time
        if(diff < 0)
            return true
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) >= this.cDays
    }

    override fun initialize(constraintAnnotation: DaysBeforeDate?) {
        //this.today = Date()
        val cal = Calendar.getInstance()
        cal.time = Date()
        cal.set(Calendar.HOUR_OF_DAY,0)
        cal.set(Calendar.MINUTE,0)
        cal.set(Calendar.SECOND,0)
        this.today = cal.time
        this.cDays = if(constraintAnnotation?.days == null || constraintAnnotation.days == 0L) this.cDays else
            constraintAnnotation.days
    }
}
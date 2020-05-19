package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.blocking.validation.annotations.Limit
import fei.stuba.gono.kotlin.blocking.validation.annotations.MaxAmount
import fei.stuba.gono.kotlin.pojo.Money
import org.springframework.beans.factory.annotation.Value
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/***
 * Class implementing validation of Money object with Limit annotation.
 * Valid amount on withdrawal must be less than limit.
 * Trieda implementujúca valídáciu objektov triedy Money s anotáciou Limit.
 * Platný objekt musí mať hodnotu premennej amount menšiu ako limit.
 * @see Limit
 */
class LimitValidator : ConstraintValidator<Limit,Money> {

    /***
     * Maximal value of the valid amount in Money object.
     * Set with either limit property of Limit annotation
     * or reportedOverlimitTransaction.limit property, default 999999999.99
     * Maximálna hodntota amount premennej platného objektu.
     * Nastavená buď premennou limit v Limit anotácii, alebo property reportedOverlimitTransaction.limit,
     * alebo predvolenou hodnota 999999999.99
     */
    @Value("\${reportedOverlimitTransaction.limit:999999999.99}")
    private var v :Double = 0.0

    override fun initialize(constraintAnnotation: Limit?) {
        this.v = if(constraintAnnotation?.maxValue == 0.0) v else
            constraintAnnotation?.maxValue!!
    }

    override fun isValid(p0: Money?, p1: ConstraintValidatorContext?): Boolean {
        if(p0 == null)
            return true
        if(p0.amount == null)
            return true
        return !(p0.amount!! > v)
    }
}
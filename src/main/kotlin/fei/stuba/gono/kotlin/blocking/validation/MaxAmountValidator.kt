package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.pojo.Money
import fei.stuba.gono.kotlin.blocking.validation.annotations.MaxAmount
import org.springframework.beans.factory.annotation.Value
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/***
 * Class implementing the validation for MaxAmount annotation. Valid amount on a withdrawal must be bigger than zero
 * and less than maximal value.
 * @see MaxAmount
 */
class MaxAmountValidator : ConstraintValidator<MaxAmount, Money> {

    /***
     * Maximal value of valid amount on a withdrawal. Set with either maxAmount property of MaxAmount annotation
     *      * or reportedOverlimitTransaction.maxAmount property, default 999999999.99
     */
    @Value("\${reportedOverlimitTransaction.maxAmount:999999999.99}")
    private var v :Double = 0.0

    override fun initialize(constraintAnnotation: MaxAmount?) {
        this.v = if(constraintAnnotation?.maxValue == 0.0) v else
            constraintAnnotation?.maxValue!!
    }

    override fun isValid(p0: Money?, p1: ConstraintValidatorContext?): Boolean {
       if(p0 == null)
           return false
        if(p0.amount == null)
            return false
        if(p0.amount!! <= 0.0)
            return false

        return !(p0.amount!! > v)
    }
}
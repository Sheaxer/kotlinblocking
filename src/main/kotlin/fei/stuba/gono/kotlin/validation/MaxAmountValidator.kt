package fei.stuba.gono.kotlin.validation

import fei.stuba.gono.kotlin.pojo.Money
import fei.stuba.gono.kotlin.validation.annotations.MaxAmount
import org.springframework.beans.factory.annotation.Value
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class MaxAmountValidator : ConstraintValidator<MaxAmount, Money> {

    @Value("\${reportedOverlimitTransaction.maxAmmount:999999999.99}")
    val customValue: Double = 0.0

    var v :Double = 0.0

    override fun initialize(constraintAnnotation: MaxAmount?) {
        this.v = if(constraintAnnotation?.maxValue == 0.0) customValue else
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
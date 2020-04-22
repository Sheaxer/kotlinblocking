package fei.stuba.gono.kotlin.blocking.validation

import fei.stuba.gono.kotlin.blocking.validation.annotations.Limit
import fei.stuba.gono.kotlin.blocking.validation.annotations.MaxAmount
import fei.stuba.gono.kotlin.pojo.Money
import org.springframework.beans.factory.annotation.Value
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class LimitValidator : ConstraintValidator<Limit,Money> {

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
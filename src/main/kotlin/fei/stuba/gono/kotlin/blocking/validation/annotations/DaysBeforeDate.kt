package fei.stuba.gono.kotlin.blocking.validation.annotations

import fei.stuba.gono.kotlin.blocking.validation.DaysBeforeDateValidator
import javax.validation.Constraint

@Target(AnnotationTarget.PROPERTY)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DaysBeforeDateValidator::class])
annotation class DaysBeforeDate(val message: String = "", val days:Long = 0) {
}
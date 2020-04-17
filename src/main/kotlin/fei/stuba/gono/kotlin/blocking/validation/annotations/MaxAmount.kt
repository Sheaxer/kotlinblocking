package fei.stuba.gono.kotlin.blocking.validation.annotations

import fei.stuba.gono.kotlin.blocking.validation.MaxAmountValidator
import javax.validation.Constraint

@Target(AnnotationTarget.PROPERTY)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [MaxAmountValidator::class])
annotation class MaxAmount(val message: String ="", val maxValue: Double = 0.0) {
}
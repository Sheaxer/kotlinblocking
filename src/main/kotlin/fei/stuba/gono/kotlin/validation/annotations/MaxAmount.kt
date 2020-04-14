package fei.stuba.gono.kotlin.validation.annotations

import fei.stuba.gono.kotlin.validation.MaxAmountValidator
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import javax.validation.Constraint

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = [MaxAmountValidator::class])
annotation class MaxAmount(val message: String ="", val maxValue: Double = 0.0) {
}
package fei.stuba.gono.kotlin.blocking.validation.annotations

import fei.stuba.gono.kotlin.blocking.validation.MaxAmountValidator
import javax.validation.Constraint
import kotlin.reflect.KClass
/***
 * Annotation for validation of Money object - withdrawal amount
 * must be more than 0 and less than maxAmount.
 */
@Target(AnnotationTarget.PROPERTY_GETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [MaxAmountValidator::class])
annotation class MaxAmount(val message: String ="", val maxValue: Double = 0.0,
                           val groups: Array<KClass<out Any>> = [],
                           val payload: Array<KClass<out Any>> = [])
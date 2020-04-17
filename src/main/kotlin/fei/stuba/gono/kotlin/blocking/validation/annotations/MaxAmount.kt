package fei.stuba.gono.kotlin.blocking.validation.annotations

import fei.stuba.gono.kotlin.blocking.validation.MaxAmountValidator
import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.PROPERTY_GETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [MaxAmountValidator::class])
annotation class MaxAmount(val message: String ="", val maxValue: Double = 0.0,
                           val groups: Array<KClass<out Any>> = [],
                           val payload: Array<KClass<out Any>> = [])
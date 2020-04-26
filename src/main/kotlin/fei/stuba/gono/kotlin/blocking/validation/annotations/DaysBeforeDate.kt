package fei.stuba.gono.kotlin.blocking.validation.annotations

import fei.stuba.gono.kotlin.blocking.validation.DaysBeforeDateValidator
import javax.validation.Constraint
import kotlin.reflect.KClass
/***
 * Annotation for validation of date - must be at least x number of days
 * (property days) in the future from the time of validation.
 */
@Target(AnnotationTarget.PROPERTY_GETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DaysBeforeDateValidator::class])
annotation class DaysBeforeDate(val message: String = "",
                                val days:Long = 0,
                                val groups: Array<KClass<out Any>> = [],
                                val payload: Array<KClass<out Any>> = [])

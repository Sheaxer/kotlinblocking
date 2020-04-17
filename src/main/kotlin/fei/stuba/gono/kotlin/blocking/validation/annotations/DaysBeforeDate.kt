package fei.stuba.gono.kotlin.blocking.validation.annotations

import fei.stuba.gono.kotlin.blocking.validation.DaysBeforeDateValidator
import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.PROPERTY_GETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DaysBeforeDateValidator::class])
annotation class DaysBeforeDate(val message: String = "",
                                val days:Long = 0,
                                val groups: Array<KClass<out Any>> = [],
                                val payload: Array<KClass<out Any>> = [])

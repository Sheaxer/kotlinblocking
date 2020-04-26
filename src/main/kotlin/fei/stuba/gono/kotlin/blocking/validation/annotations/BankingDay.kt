package fei.stuba.gono.kotlin.blocking.validation.annotations

import fei.stuba.gono.kotlin.blocking.validation.BankingDayValidator
import javax.validation.Constraint
import kotlin.reflect.KClass
/***
 * Annotation for validation of Banking day - currently any non weekend day.
 */
@Target(AnnotationTarget.PROPERTY_GETTER)
@Constraint(validatedBy = [BankingDayValidator::class])
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class BankingDay(val message:String = "",
                            val groups: Array<KClass<out Any>> = [],
                            val payload: Array<KClass<out Any>> = [])
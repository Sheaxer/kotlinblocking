package fei.stuba.gono.kotlin.blocking.validation.annotations

import fei.stuba.gono.kotlin.blocking.validation.OnlineAccountValidator
import javax.validation.Constraint
import kotlin.reflect.KClass

/***
 * Validation for AccountNO - checks if the account with the
 * AccountNO is saved and if it is online.
 */
@Constraint(validatedBy = [OnlineAccountValidator::class])
@Target(AnnotationTarget.PROPERTY_GETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class OnlineAccount (val message:String = "",
                                val groups: Array<KClass<out Any>> = [],
                                val payload: Array<KClass<out Any>> = [])
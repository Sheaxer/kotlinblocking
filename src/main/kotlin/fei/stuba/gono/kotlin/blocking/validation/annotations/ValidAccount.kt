package fei.stuba.gono.kotlin.blocking.validation.annotations

import fei.stuba.gono.kotlin.blocking.validation.AccountValidator
import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.PROPERTY_GETTER)
@Constraint(validatedBy = [AccountValidator::class])
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ValidAccount(val message: String = "", val groups: Array<KClass<out Any>> = [],
                              val payload: Array<KClass<out Any>> = [])



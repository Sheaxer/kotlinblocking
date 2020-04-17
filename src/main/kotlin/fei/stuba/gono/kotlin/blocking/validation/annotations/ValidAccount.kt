package fei.stuba.gono.kotlin.blocking.validation.annotations

import fei.stuba.gono.kotlin.blocking.validation.AccountValidator
import javax.validation.Constraint

@Target(AnnotationTarget.PROPERTY)
@Constraint(validatedBy = [AccountValidator::class])
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ValidAccount(val message: String = "")



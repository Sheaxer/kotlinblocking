package fei.stuba.gono.kotlin.validation.annotations

import fei.stuba.gono.kotlin.validation.AccountValidator
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import javax.validation.Constraint
import javax.validation.Payload

@Constraint(validatedBy = [AccountValidator::class])
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
annotation class ValidAccount(val message: String = "")



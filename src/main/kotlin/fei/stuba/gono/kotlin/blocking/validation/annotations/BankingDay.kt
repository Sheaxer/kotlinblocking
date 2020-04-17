package fei.stuba.gono.kotlin.blocking.validation.annotations

import fei.stuba.gono.kotlin.blocking.validation.BankingDayValidator
import javax.validation.Constraint

@kotlin.annotation.Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [BankingDayValidator::class])
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class BankingDay(val message:String = "") {
}
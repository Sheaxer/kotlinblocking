package fei.stuba.gono.kotlin.validation.annotations

import fei.stuba.gono.kotlin.validation.BankingDayValidator
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import javax.validation.Constraint

@Target(ElementType.FIELD)
@Constraint(validatedBy = [BankingDayValidator::class])
@Retention(RetentionPolicy.RUNTIME)
annotation class BankingDay(val message:String = "") {
}
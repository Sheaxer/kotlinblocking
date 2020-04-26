package fei.stuba.gono.kotlin.blocking.validation.annotations


import fei.stuba.gono.kotlin.blocking.validation.CurrencyAndCategoryValidator
import javax.validation.Constraint
import kotlin.reflect.KClass
/***
 * Annotation for validation of ReportedOverlimitTransaction - transaction cannot have
 * Category DOMESTIC and use non EUR currency or have category FX and currency EUR.
 */
@Target(AnnotationTarget.CLASS)
@Constraint(validatedBy = [CurrencyAndCategoryValidator::class])
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CurrencyAndCategory (val message : String = "",
                                      val groups: Array<KClass<out Any>> = [],
                                      val payload: Array<KClass<out Any>> = []
)
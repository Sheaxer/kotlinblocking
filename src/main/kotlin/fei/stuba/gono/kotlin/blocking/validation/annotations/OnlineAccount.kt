package fei.stuba.gono.kotlin.blocking.validation.annotations

import fei.stuba.gono.kotlin.blocking.validation.OnlineAccountValidator
import javax.validation.Constraint
import kotlin.reflect.KClass
@Constraint(validatedBy = [OnlineAccountValidator::class])
@Target(AnnotationTarget.PROPERTY_GETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class OnlineAccount (val message:String = "",
                                val groups: Array<KClass<out Any>> = [],
                                val payload: Array<KClass<out Any>> = [])
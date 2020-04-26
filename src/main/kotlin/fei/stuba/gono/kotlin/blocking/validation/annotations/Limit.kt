package fei.stuba.gono.kotlin.blocking.validation.annotations

import kotlin.reflect.KClass
/***
 * Annotation for validation of Money object - withdrawal amount
 * must be less than limit.
 */
@Target(AnnotationTarget.PROPERTY_GETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Limit (val message: String ="", val maxValue: Double = 0.0,
                        val groups: Array<KClass<out Any>> = [],
                        val payload: Array<KClass<out Any>> = [])
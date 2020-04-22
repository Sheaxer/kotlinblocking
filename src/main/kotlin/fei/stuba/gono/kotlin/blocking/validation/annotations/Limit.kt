package fei.stuba.gono.kotlin.blocking.validation.annotations

import kotlin.reflect.KClass
@Target(AnnotationTarget.PROPERTY_GETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Limit (val message: String ="", val maxValue: Double = 0.0,
                        val groups: Array<KClass<out Any>> = [],
                        val payload: Array<KClass<out Any>> = [])
package fei.stuba.gono.kotlin.pojo

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class Money {
    @get:NotNull
    var currency:Currency?=null
    @get:NotNull
    @get:Positive(message = "FIELD_INVALID")
    var amount: Double? = null

}
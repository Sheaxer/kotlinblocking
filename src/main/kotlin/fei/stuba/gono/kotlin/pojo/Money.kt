package fei.stuba.gono.kotlin.pojo

import javax.validation.constraints.NotNull

class Money {
    @NotNull
    var currency:Currency?=null
    @NotNull
    var amount: Double? = null

}
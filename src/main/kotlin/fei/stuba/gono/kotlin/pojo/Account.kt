package fei.stuba.gono.kotlin.pojo

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Account(

    var iban: String? = null,
    var bic: String? = null,
    var localAccountNumber: String? = null
)
package fei.stuba.gono.kotlin.pojo

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.annotation.Id

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AccountNO(

        var iban: String? = null,
        var bic: String? = null,
        var localAccountNumber: String? = null)
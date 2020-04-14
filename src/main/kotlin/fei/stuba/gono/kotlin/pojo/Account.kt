package fei.stuba.gono.kotlin.pojo

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
 class Account {

    var iban: String? = null
    var bic: String? = null
    var localAccountNumber: String? = null
}
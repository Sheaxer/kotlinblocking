package fei.stuba.gono.kotlin.pojo

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "accounts")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Account(

        var iban: String? = null,
        var bic: String? = null,
        var localAccountNumber: String? = null,
        @JsonIgnore
    var isActive : Boolean? = null,
        @Id
    @JsonIgnore
    var id: String? = null

)
package fei.stuba.gono.kotlin.blocking.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import fei.stuba.gono.kotlin.pojo.Account
import lombok.extern.slf4j.Slf4j

@Slf4j
class AccountDeserializer(vc: Class<*>?) : StdDeserializer<Account>(vc) {

    constructor() : this(null)

    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): Account? {
        var iban:String? = null
        var bic:String? = null
        var localAccountNumber:String? = null
        var a = p0?.nextFieldName()
        while (a!= null)
        {
            if(a == "iban")
                iban = p0?.nextTextValue()
            else if(a== "bic")
                bic = p0?.nextTextValue()
            else if(a=="localAccountNumber")
                localAccountNumber = p0?.nextTextValue()
            else
                p0?.nextTextValue()
            a = p0?.nextFieldName()
        }
        return Account(iban=iban, bic = bic, localAccountNumber = localAccountNumber)
    }

}
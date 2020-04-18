package fei.stuba.gono.kotlin.blocking.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import fei.stuba.gono.kotlin.pojo.Account

class AccountSerializer(t: Class<Account>?) : StdSerializer<Account>(t) {
    constructor() : this(null)

    override fun serialize(p0: Account?, p1: JsonGenerator?, p2: SerializerProvider?) {

    }
}
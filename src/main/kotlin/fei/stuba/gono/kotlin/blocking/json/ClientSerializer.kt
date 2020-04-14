package fei.stuba.gono.kotlin.blocking.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.stereotype.Component

@Component
class ClientSerializer(t: Class<Client>?) : StdSerializer<Client>(t) {
    constructor() : this(null)



    override fun serialize(p0: Client?, p1: JsonGenerator?, p2: SerializerProvider?) {
        if (p0 != null) {
            p1?.writeString(p0.id)
        }
    }
}
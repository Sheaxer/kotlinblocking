package fei.stuba.gono.kotlin.blocking.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import fei.stuba.gono.kotlin.blocking.services.ClientService
import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.fasterxml.jackson.databind.deser.std.StdDeserializer as StdDeserializer
@Component
class ClientDeserializer(vc: Class<*>?) : StdDeserializer<Client>(vc) {

    constructor() : this(null)

    @Autowired
    var clientService: ClientService? = null

    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): Client? {
        return p0?.text?.let { clientService?.getClientById(it) }
    }
}
package fei.stuba.gono.kotlin.blocking.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import fei.stuba.gono.kotlin.blocking.services.OrganisationUnitService
import fei.stuba.gono.kotlin.pojo.OrganisationUnit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrganisationUnitDeserializer(vc: Class<*>?) : StdDeserializer<OrganisationUnit>(vc) {

    constructor(): this(null)

    @Autowired
    var organisationUnitService : OrganisationUnitService? = null

    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): OrganisationUnit? {
        return p0?.text?.let { organisationUnitService?.getOrganisationUnitById(it) }
    }
}
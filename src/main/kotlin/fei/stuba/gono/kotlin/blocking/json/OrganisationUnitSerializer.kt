package fei.stuba.gono.kotlin.blocking.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import fei.stuba.gono.kotlin.pojo.OrganisationUnit
import org.springframework.stereotype.Component
/***
 *  Class that serializes OrganisationUnit by writing its id into the JsonGenerator.
 *  Trieda, ktorá serializuje objekty triedy OrganisationUnit do Json reťazca zapísaním
 *  id objektu do JsonGenerator-a.
 */
@Component
class OrganisationUnitSerializer(t: Class<OrganisationUnit>?) : StdSerializer<OrganisationUnit>(t) {

    constructor() : this(null)

    override fun serialize(p0: OrganisationUnit?, p1: JsonGenerator?, p2: SerializerProvider?) {
        if (p0 != null) {
            p1?.writeString(p0.id)
        }
    }
}
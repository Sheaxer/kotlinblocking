package fei.stuba.gono.kotlin.blocking.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.stereotype.Component
/***
 * Class that serializes Employee by writing its id into the JsonGenerator.
 */
@Component
class EmployeeSerializer(t: Class<Employee>?) : StdSerializer<Employee>(t) {
    constructor(): this(null)

    override fun serialize(p0: Employee?, p1: JsonGenerator?, p2: SerializerProvider?) {
        if (p0 != null) {
            p1?.writeString(p0.id)
        }
    }

}
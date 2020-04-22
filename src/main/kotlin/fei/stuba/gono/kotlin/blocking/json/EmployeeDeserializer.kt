package fei.stuba.gono.kotlin.blocking.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import fei.stuba.gono.kotlin.blocking.services.EmployeeService
import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EmployeeDeserializer(vc: Class<*>?) : StdDeserializer<Employee>(vc) {

    constructor() : this(null)
    @Autowired
    var employeeService: EmployeeService? = null

    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): Employee? {
      return  p0?.text?.let { employeeService?.getEmployeeById(it)?.orElse(null) }
    }

}
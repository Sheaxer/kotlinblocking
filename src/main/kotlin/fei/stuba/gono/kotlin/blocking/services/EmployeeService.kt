package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.stereotype.Service
import java.util.*


interface EmployeeService {

    fun findEmployeeByUserName(userName : String) : Optional<Employee>

    fun saveEmployee(employee: Employee): Boolean

    fun getEmployeeById(id:String) : Optional<Employee>
}
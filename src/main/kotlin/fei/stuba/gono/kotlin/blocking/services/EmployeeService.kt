package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.stereotype.Service

@Service
interface EmployeeService {

    fun findEmployeeByUserName(userName : String) : Employee?

    fun saveEmployee(employee: Employee): Boolean

    fun getEmployeeById(id:String) : Employee?
}
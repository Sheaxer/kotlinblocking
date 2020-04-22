package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.EmployeeRepository
import fei.stuba.gono.kotlin.blocking.services.EmployeeService
import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeServiceImpl @Autowired constructor(private val employeeRepository: EmployeeRepository):
        EmployeeService {


    override fun findEmployeeByUserName(userName: String): Optional<Employee> =
            employeeRepository.findEmployeeByUsername(userName)


    override fun saveEmployee(employee: Employee): Boolean {
        TODO("Not yet implemented")
    }

    override fun getEmployeeById(id: String): Optional<Employee> =  employeeRepository.findById(id)
}
package fei.stuba.gono.kotlin.blocking.mongo.services

import fei.stuba.gono.kotlin.blocking.mongo.repositories.EmployeeRepository
import fei.stuba.gono.kotlin.blocking.services.EmployeeService
import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.validation.Valid

/***
 * Implementation of EmployeeService for use with MongoDB.
 */
@Service
class EmployeeServiceImpl @Autowired constructor(private val employeeRepository: EmployeeRepository,
        private val nextSequenceService: NextSequenceService,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder):
        EmployeeService {



    @Value("\${reportedOverlimitTransaction.employee.sequenceName:employeeSequence}")
    private val sequenceName: String = "employeeSequence"



    override fun findEmployeeByUsername(userName: String): Optional<Employee> =
            employeeRepository.findEmployeeByUsername(userName)


    override fun saveEmployee(@Valid employee: Employee): Boolean {
        if(employeeRepository.existsByUsername(employee.username!!))
            return false
        employee.id = nextSequenceService.getNewId(employeeRepository,sequenceName)
        employee.password = bCryptPasswordEncoder.encode(employee.password)
        employeeRepository.save(employee)
        return true
    }

    override fun getEmployeeById(id: String): Optional<Employee> =  employeeRepository.findById(id)
}
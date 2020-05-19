package fei.stuba.gono.kotlin.blocking.rest

import fei.stuba.gono.kotlin.blocking.services.EmployeeService
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
/***
 * Rest Controller allowing POST method to endpoint /signup to register a new employee.
 * Rest kontrolér poskytujúci POST metódou na /signup endpoint-e, ktorá umožnuje
 * pridanie nového zamestnanca.
 */
@RestController
class SignUpController @Autowired constructor(private val employeeService: EmployeeService) {
    @PostMapping(value = ["/signup"], consumes = ["application/json"])
    @ResponseBody
    fun signUp(@RequestBody @Valid user: Employee): Employee {
        val e= employeeService.saveEmployee(user)
        return e ?: throw ReportedOverlimitTransactionBadRequestException("USERNAME_ALREADY_EXISTS")
    }
}
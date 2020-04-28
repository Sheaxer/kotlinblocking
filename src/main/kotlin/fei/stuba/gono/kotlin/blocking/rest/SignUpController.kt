package fei.stuba.gono.kotlin.blocking.rest

import fei.stuba.gono.kotlin.blocking.services.EmployeeService
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class SignUpController @Autowired constructor(private val employeeService: EmployeeService) {

    @PostMapping(value = ["/signup"], consumes = ["application/json"])
    @ResponseBody
    fun signUp(@RequestBody @Valid user: Employee): String? {
        return if (employeeService.saveEmployee(user))
            "SUCCESSFULLY_REGISTERED" else
            throw ReportedOverlimitTransactionBadRequestException("USERNAME_ALREADY_EXISTS")
    }
}
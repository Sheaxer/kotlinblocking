package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface EmployeeRepository : MongoRepository<Employee,String> {

    fun findEmployeeByUserName(username: String) : Optional<Employee>
}
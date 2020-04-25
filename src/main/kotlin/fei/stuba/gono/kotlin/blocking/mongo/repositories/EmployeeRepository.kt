package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
@Repository
interface EmployeeRepository : CrudRepository<Employee,String> {

    fun findEmployeeByUsername(username: String) : Optional<Employee>
}
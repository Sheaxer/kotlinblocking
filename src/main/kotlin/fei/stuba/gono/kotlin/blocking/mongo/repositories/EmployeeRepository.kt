package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
/***
 * Interface extending CrudRepository for Employee.
 * @see Employee
 * @see CrudRepository
 */
@Repository
interface EmployeeRepository : CrudRepository<Employee,String> {
    /***
     * Finds the entity with the given user name.
     * @param username User
     * @return Optional containing the entity or Optional.empty() if no entity is found.
     */
    fun findEmployeeByUsername(username: String) : Optional<Employee>

    fun existsByUsername(username: String): Boolean
}
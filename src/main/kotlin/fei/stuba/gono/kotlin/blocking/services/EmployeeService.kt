package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.stereotype.Service
import java.util.*

/***
 * Interface for marshalling and de-marshalling Employee entities.
 */
interface EmployeeService {
    /***
     * Finds the entity with the given user name.
     * @param userName user name of the entity.
     * @return Optional containing the entity or Optional.empty() if no entity was found.
     */
    fun findEmployeeByUsername(userName : String) : Optional<Employee>

    /***
     * Saves the entity.
     * @param employee entity to be saved.
     * @return true if saved, false if not - employee already exists.
     */
    fun saveEmployee(employee: Employee): Boolean

    /***
     * Finds the entity with the given id.
     * @param id id of entity.
     * @return Optional containing the entity or Optional.empty() if no entity was found.
     */
    fun getEmployeeById(id:String) : Optional<Employee>

    fun existsByUsername(userName: String):Boolean
}
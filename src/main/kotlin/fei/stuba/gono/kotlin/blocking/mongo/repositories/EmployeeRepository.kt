package fei.stuba.gono.kotlin.blocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
/***
 * Interface extending CrudRepository for Employee.
 * Rozhranie rozširujúce CrudRepository pre objekty triedy Employee.
 * @see Employee
 * @see CrudRepository
 */
@Repository
interface EmployeeRepository : CrudRepository<Employee,String> {
    /***
     * Finds the entity with the given user name.
     * Nájde entitu so zadaným používateľským menom.
     * @param username user name.
     *                 používateľské meno.
     * @return Optional containing the entity or Optional.empty() if no entity is found.
     * Optional obsahujúci entitu alebo Optional.empty(), ak entita nebola nájdená.
     */
    fun findEmployeeByUsername(username: String) : Optional<Employee>

    /***
     * Checks if entity with the given user name exists.
     * Skontroluje či entita so zadaným používateľským menom existuje.
     * @param username user name.
     *                 používateľské meno.
     * @return true if entity exists, false otherwise.
     * true ak entita existuje, false inak.
     */
    fun existsByUsername(username: String): Boolean
}
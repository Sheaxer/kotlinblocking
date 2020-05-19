package fei.stuba.gono.kotlin.blocking.services

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.stereotype.Service
import java.util.*

/***
 * Interface for marshalling and de-marshalling Employee entities.
 * Rozhranie pre mashalling a de-marshalling entít triedy Employee.
 */
interface EmployeeService {
    /***
     * Finds the entity with the given user name.
     * Nájde entitu so zadaným použivateľským menom.
     * @param userName user name of the entity.
     *                 používateľské meno entity.
     * @return Optional containing the entity or Optional.empty() if no entity was found.
     * Optional obsahujúci entitu alebo Optional.empty(), ak entita neexistuje.
     */
    fun findEmployeeByUsername(userName : String) : Optional<Employee>

    /***
     * Saves the entity.
     * Uloží entitu.
     * @param employee entity to be saved.
     *                 entita, ktorá sa má uložiť.
     * @return true if saved, false if not - employee already exists.
     * true ak entita bola uložená, false ak už entita s rovnakým požívateľským existovala.
     */
    fun saveEmployee(employee: Employee): Employee?

    /***
     * Finds the entity with the given id.
     * Nájde entitu so zadaným id.
     * @param id id of entity.
     *           id entity.
     * @return Optional containing the entity or Optional.empty()
     * if no entity was found.
     * Optional obsahujúci entitu alebo Mono.emtpy() ak entita neexistuje.
     */
    fun getEmployeeById(id:String) : Optional<Employee>

    /***
     * Checks if the entity with the given user name exists.
     * Skontroluje, či entita so zadaným používateľským menom existuje.
     * @param userName user name of the entity.
     *                 používateľské meno entity.
     * @return true if entity exists, false otherwise.
     * true, ak entita existuje, inak false.
     */
    fun existsByUsername(userName: String):Boolean
}
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
 * Implementation of EmployeeService using CRUD operations and auto generated instance of
 * EmployeeRepository. Uses NextSequenceService instance for generating new ids to save entities.
 * Implementácia rozhrania EmployeeService pomocou CRUD operácií a automaticky generovanej
 * inštancie rozhrania EmployeeRepository. Na generáciu id pri ukladaní nových entít využíva inštanciu
 * triedy NextSequenceService.
 * @param bCryptPasswordEncoder  Password encoder used to store salted and hashed passwords. Kódovač hesiel na
 * ukladanie hašovaných hesiel s tzv. "salt-om".
 * @see EmployeeRepository
 * @see NextSequenceService
 */
@Service
class EmployeeServiceImpl @Autowired constructor(private val employeeRepository: EmployeeRepository,
        private val nextSequenceService: NextSequenceService,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder):
        EmployeeService {


    /***
     * Name of the sequence that stores data for generating new id.
     * Názov sekvencie, ktorá uchováva informáciu potrebnú na generáciu nového id.
     */
    @Value("\${reportedOverlimitTransaction.employee.sequenceName:employeeSequence}")
    private val sequenceName: String = "employeeSequence"



    override fun findEmployeeByUsername(userName: String): Optional<Employee> =
            employeeRepository.findEmployeeByUsername(userName)

    /***
     *  Hashes password of employee using BCrypt and saves the entity
     *  into the repository with id generated by NextSequenceService.
     *  Hašuje heslo entity pomocou CBrypt algoritmu a uloží entitu s id
     *  generovaným inštanciou triedy NextSequenceService.
     * @param employee entity to be saved.
     *                 entita na uloženie.
     * @return true if entity was saved, false if entity with the same username
     * is already present in the repository.
     * true, ak entita bola uložená, false, ak entita s rovnakým používateľským menom už existuje.
     *
     */
    override fun saveEmployee(@Valid employee: Employee): Employee? {
        if(employeeRepository.existsByUsername(employee.username!!))
            return null
        employee.id = nextSequenceService.getNewId(employeeRepository,sequenceName)
        employee.password = bCryptPasswordEncoder.encode(employee.password)
        return employeeRepository.save(employee)
    }

    override fun getEmployeeById(id: String): Optional<Employee> =  employeeRepository.findById(id)

    override fun existsByUsername(userName: String): Boolean = employeeRepository.existsByUsername(userName)
}
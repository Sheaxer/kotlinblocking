package fei.stuba.gono.kotlin.blocking.security

import fei.stuba.gono.kotlin.blocking.services.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
/***
 * Implementation of UserDetailsService for logging into system. Checks
 * if the log in information identify an Employee entity.
 * Implementácia rozhrania UserDetailsService pre prihlásenie do systému.
 * Skontroluje, či prihlasovacie údaje identifikujú entitu triedy Employee.
 */
@Service
class UserDetailsServiceImpl @Autowired constructor(
        private val employeeService: EmployeeService): UserDetailsService {

    /***
     * Finds Employee entity with the given user name via EmployeeService instance.
     * Nájde entitu triedy Employee so zadaným menom pomocou inštancie EmployeeService.
     * @param p0 user name.
     *          používateľské meno.
     * @return instance of User with user name and password retrieved from Employee entity.
     * inštancia triedy User s používateľským menom a heslom získaným z entity triedy Employee.
     * @throws UsernameNotFoundException thrown if there is no Employee entity with the given user name.
     *
     * výnimka vyvolaná, ak neexistuje entita triedy Employee so zadaným používateľským menom.
     */
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(p0: String?): UserDetails {
        if(p0==null)
            throw(UsernameNotFoundException("NO_USERNAME"))
        val employee = employeeService.findEmployeeByUsername(p0).orElseThrow {
            UsernameNotFoundException("USERNAME_NOTFOUND")
        }
        return User(employee.username,employee.password, mutableListOf())
    }
}
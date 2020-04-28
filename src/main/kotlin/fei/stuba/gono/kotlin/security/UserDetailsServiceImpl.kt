package fei.stuba.gono.kotlin.security

import fei.stuba.gono.kotlin.blocking.services.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl @Autowired constructor(
        private val employeeService: EmployeeService): UserDetailsService {


    override fun loadUserByUsername(p0: String?): UserDetails {
        if(p0==null)
            throw(UsernameNotFoundException("NO_USERNAME"))
        val employee = employeeService.findEmployeeByUsername(p0).orElseThrow {
            UsernameNotFoundException("USERNAME_NOTFOUND")
        }
        return User(employee.username,employee.password, mutableListOf())
    }
}
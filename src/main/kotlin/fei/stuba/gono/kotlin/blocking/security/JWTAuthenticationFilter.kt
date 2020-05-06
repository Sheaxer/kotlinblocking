package fei.stuba.gono.kotlin.blocking.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import fei.stuba.gono.kotlin.pojo.Employee
import fei.stuba.gono.kotlin.security.SecurityConstants
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.core.userdetails.User
import java.io.IOException
import java.lang.RuntimeException
import java.util.*


/***
 * Class that implements UsernamePasswordAuthenticationFilter for authentication
 * for access to ReportedOverlimitTransaction REST resource for bank employees.
 * Allows authentication with username and password for employees. Checks the provided username
 * and password and if valid issues a JWT that enables access to the resources.
 * @see com.auth0.jwt.JWT
 * @see UsernamePasswordAuthenticationFilter
 */
class JWTAuthenticationFilter(private val authenticationManager1: AuthenticationManager):
        UsernamePasswordAuthenticationFilter() {



    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
       // return super.attemptAuthentication(request, response)
        val creds = ObjectMapper().readValue(request.inputStream,Employee::class.java)
        try {
            return authenticationManager1.authenticate(UsernamePasswordAuthenticationToken(
                    creds.username, creds.password, mutableListOf()
            ))
        }
        catch (ex: IOException)
        {
            throw RuntimeException(ex)
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          chain: FilterChain,
                                          authResult: Authentication) {
        val token = JWT.create().withSubject((authResult.principal as User).username).
                withExpiresAt(Date(System.currentTimeMillis() + SecurityConstants.EXPIRE_LENGTH)).
                sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY.toByteArray()))
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token)
    }
}

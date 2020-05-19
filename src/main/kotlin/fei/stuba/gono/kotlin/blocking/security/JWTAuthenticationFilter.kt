package fei.stuba.gono.kotlin.blocking.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import fei.stuba.gono.kotlin.pojo.Employee
import fei.stuba.gono.kotlin.security.SecurityConstants
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
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
 * Trieda, ktorá implementuje rozhranie UsernamePasswordAuthenticationFilter
 * na overenie prístupu k REST zdrojom pre bankových zamestnancov. Skontroluje či poslané
 * používateľské meno a heslo sú korektný pár a ak áno, vydá JWT ktorý umožnuje prístup k zdrojom.
 *
 * @see com.auth0.jwt.JWT
 * @see UsernamePasswordAuthenticationFilter
 */
class JWTAuthenticationFilter(private val authenticationManager1: AuthenticationManager):
        UsernamePasswordAuthenticationFilter() {


    /***
     * Checks if HTTP request contains username and password fields
     * and attempts authentication by checking if employee with the same username and password exists.
     * Skontroluje či HTTP požiadavka obsahuje username a password polia a pokúsi sa
     * vykonať overenie totožnosti skontrolovaním či existuje entita s rovnakým používateľským menom a
     * heslom.
     * @param request HTT request
     *                HTTP požiadavka
     * @param response HTTP response
     *                 HTTP odpoveď
     * @return Authentication
     * @throws AuthenticationException exception.
     * výnímka.
     */
    @Throws(AuthenticationException::class)
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

    /***
     * Generates JWT upon successful authentication and adds it to Authorization
     * header of HTTP response
     * Generuje JWT po úspešnom overení totožnosti a pridá ho do Authorization sekcie hlavičky
     * HTTP odpovede.
     * @param request server request
     *            požiadavka na server
     * @param response server response
     *            odpoveď servera
     * @param chain chain of filters of request
     *              reťaz filterov server požiadavky na server
     * @param authResult authentication token
     *             overovací token.
     */
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

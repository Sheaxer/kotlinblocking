package fei.stuba.gono.kotlin.blocking.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import fei.stuba.gono.kotlin.blocking.services.EmployeeService
import fei.stuba.gono.kotlin.security.SecurityConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
/***
 * Class implementing BasicAuthenticationFilter, using JWT. Checks if the Authorization header
 * field of HTTP request
 * is correctly formed e.g. contains "Bearer JWT", verifies the JWT signature and checks if it contains valid employee
 * credentials.
 * Trieda implementujúca rozhranie BasicAuthenticationFilter pomocou JWT. Skontroluje
 * či Authorization hlavička HTTP požiadavky obsahuje "Bearer JWT", kde JWT je korektný JWT a skontroluje,
 * či obsahuje správne údaje o zamestnancovi.
 */
class JWTAuthorizationFilter (authenticationManager: AuthenticationManager, private val employeeService: EmployeeService):
        BasicAuthenticationFilter(authenticationManager){


    /***
     * Checks if the HTTP request header has Authorization field that starts
     * with "Bearer " prefix and if so
     * attempts to verify that it contains valid JWT with valid credentials identifying Employee entity with
     * getAuthentication method.
     * Skontroluje, či Authorization pole HTTP hlavičky začína prefixom "Bearer " a ak
     * áno, pokúsi sa verifikovať či zvyšok pola obsahuje správny JWT s korektnými údajmi, zodpovedajúcimi
     * skutočnej entite triedy Employee.
     * pomocou getAuthentication metódy.
     * @param request HTTP request.
     *                HTTP požiadavka.
     * @param response response
     * @param chain chain
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader(SecurityConstants.HEADER_STRING)

        if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX))
        {
            chain.doFilter(request,response)
            return
        }

        val authenticationToken = getAuthentication(request)
        SecurityContextHolder.getContext().authentication = authenticationToken
        chain.doFilter(request,response)
    }

    /***
     * Extracts the JWT from Authorization field of HTTP request,
     * verifies the signature and checks if it contains
     * valid employee credentials. Uses HMAC and SHA-512.
     * Extrahuje JWT z Authorization sekcie HTTP požiadavky, verifikuje
     * jeho signatúru a skontroluje, či obsahuje korektné informácie o zamestnancovi.
     * @param request HTTP request
     * @return Token granting authorization to access secured endpoints
     * if JWT with valid credentials identifying Employee entity was provided, null otherwise.
     * Token povolujúci prístup k zabezpečeným endpoint-om ak bol poskytnutý
     * platný JWT identifikujúici existujúcu entitu triedy Employee, inak null.
     */
    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken?
    {
        val token = request.getHeader(SecurityConstants.HEADER_STRING)
        if (token != null) {
            return try{
                val user: String = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY.toByteArray()))
                        .build()
                        .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                        .subject
                if(employeeService.existsByUsername(user))
                    UsernamePasswordAuthenticationToken(user, null, ArrayList())
                else null

            }catch (ex: com.auth0.jwt.exceptions.JWTDecodeException) {
                null
            }

        }
        return null
    }


}
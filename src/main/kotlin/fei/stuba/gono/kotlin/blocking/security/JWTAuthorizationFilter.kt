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
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter (authenticationManager: AuthenticationManager, private val employeeService: EmployeeService):
        BasicAuthenticationFilter(authenticationManager){



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
package fei.stuba.gono.kotlin.blocking.security

import fei.stuba.gono.kotlin.blocking.security.JWTAuthenticationFilter
import fei.stuba.gono.kotlin.blocking.security.JWTAuthorizationFilter
import fei.stuba.gono.kotlin.blocking.services.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
/***
 *  Class providing security configuration for the endpoints.
 * Sets up authorization using JWTs. Allows
 * unrestricted access to /login endpoint and secures all others with requiring
 * to add Authorization field in HTTP header
 * containing valid JWT with valid credentials of an employee.
 * Trieda zabezpečuje nastavenie bezpečnosti pre endpoint-y.
 * Povolí prístup na /login a /signup endpoint, a zabezpečí všetky ostatné endpoint-y, vyžadovaním
 * pridania Authorization poľa v HTTP hlavičke, ktoré obsahuje platný JWT s údajmi o zamestnancovi
 * - objektu triedy Employee.
 */
@EnableWebSecurity
class WebSecurity @Autowired constructor(@Qualifier("userDetailsServiceImpl") private val
                  userDetailsService: UserDetailsService,
                                         private val bCryptPasswordEncoder: BCryptPasswordEncoder):
        WebSecurityConfigurerAdapter() {


    /***
     * Sets up authorization using the UserDetailsService and adds BCryptPasswordEncoder as
     * password encoder.
     * Nastaví autorizáciu pomocou inštancie UserDetailsService a nastaví inštanciu
     * BCryptPasswordEncoder ako enkóder hesiel
     * @param auth builder of AuthenticationManager instance.
     *             tvorca inštancie AuthenticationManager.
     * @throws Exception exception.
     * výnimka.
     * @see BCryptPasswordEncoder
     * @see UserDetailsService
     */
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder)
    }

    /***
     * Permits access to SIGN_UP_URL and sets need to authenticate access to any other endpoints. Adds
     * JWTAuthenticationFilter and JWTAuthorizationFilter to security filters and sets STATELESS session creation
     * policy.Povolí prístup k SIGN_UP_URL a nastaví potrebu authentikácie na povolenie
     * prístupu k iným endpointom. Pridá inštancie tried JWTAuthenticationFilter a JWTAuthorizationFilter do
     * bezpečnostných filtrov a nastaví politiku vytvárania session-ov na STATELESS.
     * @see JWTAuthenticationFilter
     * @see JWTAuthorizationFilter
     * @see SessionCreationPolicy
     * @param http HttpSecurity
     * @throws Exception Exception
     */
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable().authorizeRequests().
                antMatchers(HttpMethod.POST,"/signup").permitAll().
                anyRequest().authenticated().
                and().
                addFilter(JWTAuthenticationFilter(authenticationManager())).
                addFilter(JWTAuthorizationFilter(authenticationManager(),
                        applicationContext.getBean(EmployeeService::class.java))).
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    /***
     * Allows Cors for any URL source.
     * Umožní CORS pre všetky URL zdroje.
     * @return UrlBasedCorsConfigurationSource instance.
     * inštancia triedy UrlBasedCorsConfigurationSource .
     */
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
        return source
    }
}



package fei.stuba.gono.kotlin.blocking.security

import fei.stuba.gono.kotlin.blocking.security.JWTAuthenticationFilter
import fei.stuba.gono.kotlin.blocking.security.JWTAuthorizationFilter
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

@EnableWebSecurity
class WebSecurity @Autowired constructor(@Qualifier("userDetailsServiceImpl") private val
                  userDetailsService: UserDetailsService,
                                         private val bCryptPasswordEncoder: BCryptPasswordEncoder): WebSecurityConfigurerAdapter() {



    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable().authorizeRequests().
                antMatchers(HttpMethod.POST,"/signup").permitAll().
                anyRequest().authenticated().
                and().
                addFilter(JWTAuthenticationFilter(authenticationManager())).
                addFilter(JWTAuthorizationFilter(authenticationManager())).
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
        return source
    }
}



package com.github.vanyuart.endpointmonitoring.security

import com.github.vanyuart.endpointmonitoring.security.service.UserDetailsServiceImpl
import com.github.vanyuart.endpointmonitoring.security.uuid.UUIDAuthenticationFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userDetailsService: UserDetailsServiceImpl,
    private val uUIDAuthenticationFilter: UUIDAuthenticationFilter,
    private val authenticationEntryPointImpl: AuthenticationEntryPointImpl,
) : WebSecurityConfigurerAdapter() {

    // configure Spring Security to use custom implementation of UserDetailsService
    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder.userDetailsService<UserDetailsService>(userDetailsService)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPointImpl).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests().anyRequest().authenticated()

        // add custom UUID authentication filter to the chain
        http.addFilterBefore(uUIDAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}
package com.github.vanyuart.endpointmonitoring.security.uuid

import com.github.vanyuart.endpointmonitoring.entity.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


private const val AUTH_HEADER = "Authorization"
private const val BEARER_TOKEN = "Bearer "

/**
 * This authentication filter checks Authorization header for a Bearer token.
 * If token is present then there will be an attempt to find with same [User.accessToken].
 * If user found it fills security context.
 */
class UUIDAuthenticationFilter(
    private val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        run {
            // find user by accessToken

            val authToken = parseAuthToken(request) ?: return@run
            val userDetails: UserDetails = userDetailsService.loadUserByUsername(authToken) ?: return@run

            // create authentication token
            val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

            // set up security context
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    /**
     * Return token from Bearer authorization
     */
    private fun parseAuthToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader(AUTH_HEADER)
        return if (StringUtils.hasText(authHeader) && authHeader.startsWith(BEARER_TOKEN)) {
            authHeader.substring(startIndex = BEARER_TOKEN.length)
        } else {
            null
        }
    }
}
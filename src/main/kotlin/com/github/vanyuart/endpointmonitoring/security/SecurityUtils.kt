package com.github.vanyuart.endpointmonitoring.security

import com.github.vanyuart.endpointmonitoring.entity.User
import com.github.vanyuart.endpointmonitoring.exception.NotFoundException
import com.github.vanyuart.endpointmonitoring.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

/**
 * Utils for security context
 */
@Component
object SecurityUtils {

    @Autowired
    private lateinit var userRepository: UserRepository

    /**
     * Try to get a user from security context
     */
    fun getCurrentUser(): User {
        val context = SecurityContextHolder.getContext()
        val userDetails = context?.authentication?.principal as UserDetails
        return userRepository.findByUsername(userDetails.username) ?: throw NotFoundException.create(User::javaClass.name, userDetails.username)
    }
}
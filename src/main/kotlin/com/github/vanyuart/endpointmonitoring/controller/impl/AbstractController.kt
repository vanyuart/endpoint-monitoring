package com.github.vanyuart.endpointmonitoring.controller.impl

import com.github.vanyuart.endpointmonitoring.entity.User
import com.github.vanyuart.endpointmonitoring.exception.NotFoundException
import com.github.vanyuart.endpointmonitoring.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
abstract class AbstractController {

    @Autowired
    private lateinit var userRepository: UserRepository

    /**
     * Retrieve current user from security context
     */
    fun getCurrentUser(): User {
        val context = SecurityContextHolder.getContext()
        val userDetails = context?.authentication?.principal as UserDetails
        return userRepository.findByUsername(userDetails.username)
            ?: throw NotFoundException.create(User::class.simpleName, userDetails.username)
    }
}
package com.github.vanyuart.endpointmonitoring.security.service

import com.github.vanyuart.endpointmonitoring.repository.UserRepository
import org.springframework.context.annotation.Primary
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Primary
@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository,
) : UserDetailsService {

    /**
     * For the sake of simplicity seeded user will be found by accessToken
     */
    @Transactional(readOnly = true)
    override fun loadUserByUsername(authTokem: String): UserDetails? {
        val user = userRepository.findByAccessToken(authTokem) ?: return null
        return UserDetailsImpl(
            username = user.username,
            // simplified for the interview task, user does not have password or roles
            password = user.username,
            authorities = listOf(SimpleGrantedAuthority("USER"))
        )
    }
}
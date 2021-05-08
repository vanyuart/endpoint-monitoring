package com.github.vanyuart.endpointmonitoring.configuration

import com.github.vanyuart.endpointmonitoring.entity.User
import com.github.vanyuart.endpointmonitoring.repository.UserRepository
import com.github.vanyuart.endpointmonitoring.util.logger
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener

@Configuration
class UserSeeder(
    private val userRepository: UserRepository,
) {
    private val log by logger()

    @EventListener
    fun seed(event: ContextRefreshedEvent) {
        createUserIfNotExists(username = "user", accessToken = "bla")
        createUserIfNotExists(username = "user2", accessToken = "bla2")
    }

    private fun createUserIfNotExists(username: String, accessToken: String) {
        if (!userRepository.existsByUsername(username)) {
            log.info("Seeding user with username=${username} accessToken=${accessToken}")
            val user = User(username = username, accessToken = accessToken)
            userRepository.save(user)
        } else {
            log.warn("User with username=${username} already exists")
        }
    }
}
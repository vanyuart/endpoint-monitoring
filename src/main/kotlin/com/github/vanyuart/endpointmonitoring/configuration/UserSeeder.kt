package com.github.vanyuart.endpointmonitoring.configuration

import com.github.vanyuart.endpointmonitoring.entity.User
import com.github.vanyuart.endpointmonitoring.repository.UserRepository
import com.github.vanyuart.endpointmonitoring.util.logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener

@Configuration
class UserSeeder(
    private val userRepository: UserRepository,

    @Value("\${endpointmonitoring.seed.user.username}")
    private val username: String,
    @Value("\${endpointmonitoring.seed.user.accessToken}")
    private val accessToken: String,

    @Value("\${endpointmonitoring.seed.user2.username}")
    private val username2: String,
    @Value("\${endpointmonitoring.seed.user2.accessToken}")
    private val accessToken2: String,
) {
    private val log by logger()


    @EventListener
    fun seed(event: ContextRefreshedEvent) {
        createUserIfNotExists(username = username, accessToken = accessToken)
        createUserIfNotExists(username = username2, accessToken = accessToken2)
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
package com.github.vanyuart.endpointmonitoring.service

import com.github.vanyuart.endpointmonitoring.entity.User
import com.github.vanyuart.endpointmonitoring.exception.NotAllowedException
import com.github.vanyuart.endpointmonitoring.exception.NotFoundException
import com.github.vanyuart.endpointmonitoring.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(locations = ["classpath:application-test.properties"])
class MonitoredEndpointServiceTest
@Autowired
constructor(
    private val monitoredEndpointService: MonitoredEndpointService,
    private val userRepository: UserRepository,
) {
    private val ENDPOINT_NAME = "testEndpoint"

    @Test
    fun `User can create endpoint`() {
        var user = User(username = "test", accessToken = "test")
        user = userRepository.save(user)

        // assert user does not have endpoints
        var userEndpoints = monitoredEndpointService.getEndpointsByUser(user)
        assertEquals(0, userEndpoints.size)

        // create endpoint
        monitoredEndpointService.createMonitoredEndpoint(
            name = ENDPOINT_NAME,
            url = "https://test.com",
            monitoringInterval = 5,
            owner = user
        )

        // user has exactly one endpoint
        userEndpoints = monitoredEndpointService.getEndpointsByUser(user)
        assertEquals(1, userEndpoints.size)
        assertEquals(ENDPOINT_NAME, userEndpoints[0].name)
    }

    @Test
    fun `User can only access owned endpoint`() {
        var user = User(username = "test", accessToken = "test")
        user = userRepository.save(user)

        var otherUser = User(username = "test2", accessToken = "test2")
        otherUser = userRepository.save(otherUser)

        // create endpoint for a user
        monitoredEndpointService.createMonitoredEndpoint(
            name = ENDPOINT_NAME,
            url = "https://test.com",
            monitoringInterval = 5,
            owner = user
        )

        // attempt to get endpoint by id with another user throws exception
        val endpointId = monitoredEndpointService.getEndpointsByUser(user)[0].id
        assertThrows(NotAllowedException::class.java) {
            monitoredEndpointService.getEndpointById(endpointId, otherUser)
        }
    }
}
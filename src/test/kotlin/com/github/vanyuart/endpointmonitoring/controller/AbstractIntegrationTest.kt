package com.github.vanyuart.endpointmonitoring.controller

import com.github.vanyuart.endpointmonitoring.entity.User
import com.github.vanyuart.endpointmonitoring.repository.UserRepository
import com.github.vanyuart.endpointmonitoring.security.service.UserDetailsImpl
import com.github.vanyuart.endpointmonitoring.security.service.UserDetailsServiceImpl
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.test.web.servlet.MockMvc

const val AUTH_HEADER = "Authorization"
const val BEARER_TOKEN = "Bearer "

const val TEST_ACCESS_TOKEN = "testToken"
const val TEST_USERNAME = "testUsername"

abstract class AbstractIntegrationTest {

    private val TEST_USER = User(username = TEST_USERNAME, accessToken = TEST_ACCESS_TOKEN)
    private val TEST_USER_DETAILS = UserDetailsImpl(username = TEST_USERNAME, password = TEST_USERNAME, authorities = listOf(SimpleGrantedAuthority("USER")))

    @Autowired
    protected lateinit var mvc: MockMvc

    @MockkBean
    protected lateinit var userDetailsService: UserDetailsServiceImpl

    @MockkBean
    protected lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        every { userDetailsService.loadUserByUsername(any()) } returns null
        every { userDetailsService.loadUserByUsername(TEST_ACCESS_TOKEN) } returns TEST_USER_DETAILS
        every { userRepository.findByUsername(TEST_USERNAME) } returns TEST_USER
    }
}
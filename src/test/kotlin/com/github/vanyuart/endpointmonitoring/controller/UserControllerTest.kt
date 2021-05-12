package com.github.vanyuart.endpointmonitoring.controller

import com.github.vanyuart.endpointmonitoring.controller.impl.UserControllerImpl
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(UserControllerImpl::class)
class UserControllerTest : AbstractIntegrationTest() {

    @Test
    fun `Request with valid accessToken returns current user`() {
        mvc.perform(
            MockMvcRequestBuilders
                .get("/users/current")
                .header(AUTH_HEADER, BEARER_TOKEN + TEST_ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.username").value(TEST_USERNAME)
        )
    }

    @Test
    fun `Request with invalid token returns 401 Unauthorized`() {
        mvc.perform(
            MockMvcRequestBuilders
                .get("/users/current")
                .header(AUTH_HEADER, BEARER_TOKEN + "invalidToken")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isUnauthorized
        )
    }

    @Test
    fun `Request without Authorization header gets 401 Unauthorized`() {
        mvc.perform(
            MockMvcRequestBuilders
                .get("/users/current")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isUnauthorized
        )
    }
}
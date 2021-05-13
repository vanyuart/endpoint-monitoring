package com.github.vanyuart.endpointmonitoring.controller

import com.github.vanyuart.endpointmonitoring.controller.impl.TestControllerImpl
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(TestControllerImpl::class)
class TestControllerTest : AbstractIntegrationTest() {

    @Test
    fun `No authorization needed to access test endpoint`() {
        mvc.perform(
            MockMvcRequestBuilders
                .get("/test")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        )
    }
}
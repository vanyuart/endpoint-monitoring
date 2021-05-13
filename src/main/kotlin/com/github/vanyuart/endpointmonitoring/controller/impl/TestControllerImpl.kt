package com.github.vanyuart.endpointmonitoring.controller.impl

import com.github.vanyuart.endpointmonitoring.controller.TestController
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("test")
class TestControllerImpl: TestController {

    @GetMapping("teapot")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    override fun getATeapotAfterLongWait() {
        Thread.sleep(5000)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun getOk() {}
}
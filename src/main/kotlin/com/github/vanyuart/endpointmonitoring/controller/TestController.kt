package com.github.vanyuart.endpointmonitoring.controller

import org.springframework.http.HttpStatus

/**
 * This controller might be used for [EndpointCheckerScheduledTask] testing.
 * No authorization needed.
 */
interface TestController {

    /**
     * Return [HttpStatus.I_AM_A_TEAPOT] after 5 seconds sleep
     */
    fun getATeapotAfterLongWait()

    /**
     * Return [HttpStatus.OK] immediately
     */
    fun getOk()
}
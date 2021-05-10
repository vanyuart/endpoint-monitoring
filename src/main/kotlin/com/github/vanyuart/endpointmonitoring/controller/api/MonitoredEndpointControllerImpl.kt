package com.github.vanyuart.endpointmonitoring.controller.api

import com.github.vanyuart.endpointmonitoring.controller.MonitoredEndpointController
import com.github.vanyuart.endpointmonitoring.dto.CreateMonitorEndpointReq
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("endpoint")
class MonitoredEndpointControllerImpl(

) : MonitoredEndpointController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createEndpoint(@RequestBody req: CreateMonitorEndpointReq) {
        TODO("Not yet implemented")
    }
}
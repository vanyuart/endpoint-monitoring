package com.github.vanyuart.endpointmonitoring.controller

import com.github.vanyuart.endpointmonitoring.dto.CreateMonitorEndpointReq

interface MonitoredEndpointController {

    fun createEndpoint(req: CreateMonitorEndpointReq)
}
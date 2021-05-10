package com.github.vanyuart.endpointmonitoring.service

import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.User

interface MonitoredEndpointService {
    fun createMonitoredEndpoint(name: String, url: String, monitoringInterval: Int, owner: User)
    fun getEndpointsByUser(user: User): List<MonitoredEndpoint>
}
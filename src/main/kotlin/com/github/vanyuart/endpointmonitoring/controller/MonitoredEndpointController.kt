package com.github.vanyuart.endpointmonitoring.controller

import com.github.vanyuart.endpointmonitoring.dto.CreateMonitoredEndpointReq
import com.github.vanyuart.endpointmonitoring.dto.UpdateMonitoredEndpointReq
import com.github.vanyuart.endpointmonitoring.dto.type.MonitoredEndpointDto

interface MonitoredEndpointController {

    /**
     * Get endpoint of current user by ID
     */
    fun getUserEndpoint(endpointId: Long): MonitoredEndpointDto

    /**
     * Get all endpoints of current user
     */
    fun getUserEndpoints(): List<MonitoredEndpointDto>

    /**
     * Create new endpoint for current user
     */
    fun createEndpoint(req: CreateMonitoredEndpointReq)

    /**
     * Update existing endpoint of current user
     */
    fun updateEndpoint(req: UpdateMonitoredEndpointReq)

    /**
     * Delete endpoint of current user
     */
    fun deleteEndpoint(endpointId: Long)
}
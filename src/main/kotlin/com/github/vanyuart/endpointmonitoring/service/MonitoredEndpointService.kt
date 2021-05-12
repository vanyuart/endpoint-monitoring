package com.github.vanyuart.endpointmonitoring.service

import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.User

interface MonitoredEndpointService {

    /**
     * Get endpoint by ID
     * @throws [NotFoundException] if endpoint does not exist
     * @throws [NotAllowedException] if user cannot access given endpoint
     */
    fun getEndpointById(id: Long, user: User): MonitoredEndpoint

    /**
     * Get all endpoints by user as an owner
     */
    fun getEndpointsByUser(user: User): List<MonitoredEndpoint>

    /**
     * Create endpoint for a user
     */
    fun createMonitoredEndpoint(name: String, url: String, monitoringInterval: Int, owner: User)

    /**
     * Update endpoint
     * @throws [NotFoundException] if endpoint does not exist
     * @throws [NotAllowedException] if user cannot edit given endpoint
     */
    fun updateMonitoredEndpoint(id: Long, name: String?, url: String?, monitoringInterval: Int?, owner: User)

    /**
     * Delete endpoint by ID
     * @throws [NotFoundException] if endpoint does not exist
     * @throws [NotAllowedException] if user cannot access given endpoint
     */
    fun deleteMonitoredEndpoint(id: Long, user: User)
}
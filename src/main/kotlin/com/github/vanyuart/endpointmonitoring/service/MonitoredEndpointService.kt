package com.github.vanyuart.endpointmonitoring.service

import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.User
import com.github.vanyuart.endpointmonitoring.exception.NotAllowedException
import com.github.vanyuart.endpointmonitoring.exception.NotFoundException
import com.github.vanyuart.endpointmonitoring.scheduled.EndpointCheckerScheduledTask
import java.time.ZonedDateTime

interface MonitoredEndpointService {

    /**
     * Get endpoints eligible for the next check in [EndpointCheckerScheduledTask]
     */
    fun getEndpointsForNextCheck(): List<MonitoredEndpoint>

    /**
     * Fetch endpoint and set the date of next check
     * @param date of the last check
     */
    fun updateNextCheckDate(id: Long, lastCheckDate: ZonedDateTime)

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
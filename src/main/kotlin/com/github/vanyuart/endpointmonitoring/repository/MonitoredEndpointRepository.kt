package com.github.vanyuart.endpointmonitoring.repository

import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.time.ZonedDateTime

interface MonitoredEndpointRepository : JpaRepository<MonitoredEndpoint, Long> {
    fun findAllByOwner(owner: User): List<MonitoredEndpoint>
    fun findAllByNextCheckDateIsNullOrNextCheckDateBefore(nextCheckDateBefore: ZonedDateTime): List<MonitoredEndpoint>
}
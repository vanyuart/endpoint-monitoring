package com.github.vanyuart.endpointmonitoring.repository

import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import org.springframework.data.jpa.repository.JpaRepository

interface MonitoredEndpointRepository : JpaRepository<MonitoredEndpoint, Long> {
}
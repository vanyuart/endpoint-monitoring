package com.github.vanyuart.endpointmonitoring.repository

import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.MonitoringResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MonitoringResultRepository : JpaRepository<MonitoringResult, Long> {
    fun findTop10ByEndpointOrderByDateOfCheckDesc(endpoint: MonitoredEndpoint): List<MonitoringResult>
}
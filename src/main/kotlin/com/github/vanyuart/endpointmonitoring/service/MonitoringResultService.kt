package com.github.vanyuart.endpointmonitoring.service

import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.MonitoringResult
import java.time.ZonedDateTime

interface MonitoringResultService {

    fun save(statusCode: Int, payload: String, dateOfCheck: ZonedDateTime, endpoint: MonitoredEndpoint)

    /**
     * @return last 10 [MonitoringResult] for [MonitoredEndpoint]
     */
    fun getLast10MonitoringResults(endpoint: MonitoredEndpoint): List<MonitoringResult>
}
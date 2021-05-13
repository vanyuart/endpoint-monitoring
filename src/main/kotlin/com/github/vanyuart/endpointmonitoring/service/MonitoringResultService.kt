package com.github.vanyuart.endpointmonitoring.service

import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.MonitoringResult

interface MonitoringResultService {

    /**
     * Store [MonitoringResult]
     */
    fun save(monitoringResult: MonitoringResult)

    /**
     * @return last 10 [MonitoringResult] for [MonitoredEndpoint]
     */
    fun getLast10MonitoringResults(endpoint: MonitoredEndpoint): List<MonitoringResult>
}
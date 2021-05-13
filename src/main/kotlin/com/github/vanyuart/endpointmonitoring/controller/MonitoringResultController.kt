package com.github.vanyuart.endpointmonitoring.controller

import com.github.vanyuart.endpointmonitoring.dto.type.MonitoringResultDto

interface MonitoringResultController {
    fun getLastMonitoringResultsForEndpoint(endpointId: Long): List<MonitoringResultDto>
}
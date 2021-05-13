package com.github.vanyuart.endpointmonitoring.controller.impl

import com.github.vanyuart.endpointmonitoring.controller.MonitoringResultController
import com.github.vanyuart.endpointmonitoring.dto.toDTO
import com.github.vanyuart.endpointmonitoring.dto.type.MonitoringResultDto
import com.github.vanyuart.endpointmonitoring.service.MonitoredEndpointService
import com.github.vanyuart.endpointmonitoring.service.MonitoringResultService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController("monitoring/results")
class MonitoringResultControllerImpl(
    private val monitoringResultService: MonitoringResultService,
    private val monitoredEndpointService: MonitoredEndpointService,
) : AbstractController(), MonitoringResultController {

    @GetMapping("{endpointId}")
    override fun getLastMonitoringResultsForEndpoint(@PathVariable endpointId: Long): List<MonitoringResultDto> {
        val endpoint = monitoredEndpointService.getEndpointById(endpointId, getCurrentUser())
        return monitoringResultService.getLast10MonitoringResults(endpoint).map { it.toDTO() }
    }
}
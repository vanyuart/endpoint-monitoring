package com.github.vanyuart.endpointmonitoring.service.impl

import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.MonitoringResult
import com.github.vanyuart.endpointmonitoring.repository.MonitoringResultRepository
import com.github.vanyuart.endpointmonitoring.service.MonitoringResultService
import org.springframework.stereotype.Service

@Service
class MonitoringResultServiceImpl(
    private val monitoringResultRepository: MonitoringResultRepository,
) : MonitoringResultService {

    override fun save(monitoringResult: MonitoringResult) {
        monitoringResultRepository.save(monitoringResult)
    }

    override fun getLast10MonitoringResults(endpoint: MonitoredEndpoint): List<MonitoringResult> =
        monitoringResultRepository.findTop10ByEndpoint(endpoint)
}
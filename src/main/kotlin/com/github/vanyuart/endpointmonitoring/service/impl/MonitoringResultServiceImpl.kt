package com.github.vanyuart.endpointmonitoring.service.impl

import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.MonitoringResult
import com.github.vanyuart.endpointmonitoring.repository.MonitoringResultRepository
import com.github.vanyuart.endpointmonitoring.service.MonitoringResultService
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class MonitoringResultServiceImpl(
    private val monitoringResultRepository: MonitoringResultRepository,
) : MonitoringResultService {

    override fun save(statusCode: Int, payload: String, dateOfCheck: ZonedDateTime, endpoint: MonitoredEndpoint) {
        val monitoringResult = MonitoringResult(
            statusCode = statusCode,
            payload = payload,
            dateOfCheck = dateOfCheck,
            endpoint = endpoint
        )
        monitoringResultRepository.save(monitoringResult)
    }

    override fun getLast10MonitoringResults(endpoint: MonitoredEndpoint): List<MonitoringResult> =
        monitoringResultRepository.findTop10ByEndpoint(endpoint)
}
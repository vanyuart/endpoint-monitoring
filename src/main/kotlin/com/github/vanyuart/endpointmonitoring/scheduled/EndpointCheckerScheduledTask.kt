package com.github.vanyuart.endpointmonitoring.scheduled

import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import com.github.kittinunf.fuel.httpGet
import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.MonitoringResult
import com.github.vanyuart.endpointmonitoring.service.MonitoredEndpointService
import com.github.vanyuart.endpointmonitoring.service.MonitoringResultService
import com.github.vanyuart.endpointmonitoring.util.logger
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.concurrent.ConcurrentHashMap

/**
 * In the real world project I would use Quartz scheduler as it provides better structure to the scheduled tasks.
 * Here with only one scheduled task this should be sufficient.
 *
 * This task uses Fuel Http Client to asynchronously send http request using Kotlin coroutines.
 * Results then added to a concurrent map and later stored.
 */
@Component
class EndpointCheckerScheduledTask(
    private val monitoredEndpointService: MonitoredEndpointService,
    private val monitoringResultService: MonitoringResultService,
) {
    private val log by logger()

    @Scheduled(cron = "\${endpointmonitoring.scheduled.endpointChecker.cron}")
    fun executeTask() = runBlocking {
        log.info("EndpointCheckerScheduledTask started")

        val endpoints: List<MonitoredEndpoint> = monitoredEndpointService.getEndpointsForNextCheck()
        val endpointResultMap = ConcurrentHashMap<MonitoredEndpoint, MonitoringResult>()
        val dateOfCheck = ZonedDateTime.now()

        // await for all requests
        coroutineScope {
            endpoints.forEach { endpoint ->
                val result = fetchUrl(endpoint, dateOfCheck)
                endpointResultMap[endpoint] = result
            }
        }
        // bulk save results and update endpoint
        monitoringResultService.bulkSave(endpointResultMap.values)
        monitoredEndpointService.bulkUpdateNextCheckDate(endpoints.map { it.id }, dateOfCheck)

        log.info("EndpointCheckerScheduledTask finished.")
    }

    /**
     * Send async request to monitored endpoint
     */
    private suspend fun fetchUrl(endpoint: MonitoredEndpoint, dateOfCheck: ZonedDateTime): MonitoringResult {
        log.info("GET: ${endpoint.url}")

        val (request, response, result) = endpoint.url.httpGet().awaitStringResponseResult()
        log.info("name=${endpoint.name} statusCode=${response.statusCode} url=${endpoint.url} payload=${String(response.data)}")

        return MonitoringResult(
            statusCode = response.statusCode,
            payload = String(response.data),
            dateOfCheck = dateOfCheck,
            endpoint = endpoint
        )
    }
}

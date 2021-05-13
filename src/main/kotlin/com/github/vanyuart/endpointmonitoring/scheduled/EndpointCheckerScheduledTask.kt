package com.github.vanyuart.endpointmonitoring.scheduled

import com.github.kittinunf.fuel.httpGet
import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.MonitoringResult
import com.github.vanyuart.endpointmonitoring.service.MonitoredEndpointService
import com.github.vanyuart.endpointmonitoring.service.MonitoringResultService
import com.github.vanyuart.endpointmonitoring.util.logger
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
        val monitoringResults = ConcurrentHashMap<MonitoredEndpoint, MonitoringResult>()
        val dateOfCheck = ZonedDateTime.now()

        endpoints.forEach { endpoint ->
            log.info("GET: ${endpoint.url}")

            endpoint.url.httpGet().response { request, response, result ->
                log.info("name=${endpoint.name} statusCode=${response.statusCode} url=${endpoint.url}")

                monitoringResults[endpoint] = MonitoringResult(
                    statusCode = response.statusCode,
                    payload = response.responseMessage,
                    dateOfCheck = dateOfCheck,
                    endpoint = endpoint
                )
            }
        }
        monitoringResults.forEach { monitoringResult ->
            monitoringResultService.save(monitoringResult.value)
            monitoredEndpointService.updateNextCheckDate(monitoringResult.key.id, dateOfCheck)
        }

        log.info("EndpointCheckerScheduledTask finished.")
    }
}

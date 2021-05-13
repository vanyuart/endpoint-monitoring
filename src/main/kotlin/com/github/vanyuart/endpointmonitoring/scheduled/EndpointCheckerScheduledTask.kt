package com.github.vanyuart.endpointmonitoring.scheduled

import com.github.kittinunf.fuel.httpGet
import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.service.MonitoredEndpointService
import com.github.vanyuart.endpointmonitoring.service.MonitoringResultService
import com.github.vanyuart.endpointmonitoring.util.logger
import kotlinx.coroutines.runBlocking
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

/**
 * In the real world project I would use Quartz scheduler as it provides better structure to the scheduled tasks.
 * Here with only one scheduled task this should be sufficient.
 *
 * This task uses Fuel Http Client to asynchronously send http request using Kotlin coroutines.
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
        endpoints.forEach { endpoint ->
            val dateOfCheck = ZonedDateTime.now()

            log.info("GET: ${endpoint.url}")
            endpoint.url.httpGet().response { request, response, result ->

                log.info("name=${endpoint.name} statusCode=${response.statusCode} url=${endpoint.url}")

                monitoringResultService.save(
                    statusCode = response.statusCode,
                    payload = response.responseMessage,
                    dateOfCheck = dateOfCheck,
                    endpoint = endpoint
                )

                monitoredEndpointService.updateNextCheckDate(endpoint.id, dateOfCheck)
            }

        }

        log.info("EndpointCheckerScheduledTask finished.")
    }
}

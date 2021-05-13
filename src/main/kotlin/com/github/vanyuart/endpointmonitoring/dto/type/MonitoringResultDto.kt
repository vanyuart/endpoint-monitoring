package com.github.vanyuart.endpointmonitoring.dto.type

import java.time.ZonedDateTime

data class MonitoringResultDto(
    val statusCode: Int,
    val payload: String,
    val dateOfCheck: ZonedDateTime,
    val endpointId: Long,
)
package com.github.vanyuart.endpointmonitoring.dto.type

import java.time.ZonedDateTime

data class MonitoredEndpointDto(
    val id: Long,
    val name: String,
    val url: String,
    val monitoringInterval: Int,
    val ownerId: Long,
    val createdDate: ZonedDateTime,
    val changedDate: ZonedDateTime?,
)
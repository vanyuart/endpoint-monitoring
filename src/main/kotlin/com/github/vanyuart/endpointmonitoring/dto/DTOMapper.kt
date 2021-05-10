package com.github.vanyuart.endpointmonitoring.dto

import com.github.vanyuart.endpointmonitoring.dto.type.MonitoredEndpointDto
import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint

fun MonitoredEndpoint.toDTO() = MonitoredEndpointDto(
    id = id,
    name = name,
    url = url,
    monitoringInterval = monitoringInterval,
    ownerId = owner.id,
    createdDate = createdDate,
    changedDate = changedDate,
)
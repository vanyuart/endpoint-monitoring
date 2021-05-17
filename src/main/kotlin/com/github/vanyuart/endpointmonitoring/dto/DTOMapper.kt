package com.github.vanyuart.endpointmonitoring.dto

import com.github.vanyuart.endpointmonitoring.dto.type.MonitoredEndpointDto
import com.github.vanyuart.endpointmonitoring.dto.type.MonitoringResultDto
import com.github.vanyuart.endpointmonitoring.dto.type.UserDto
import com.github.vanyuart.endpointmonitoring.entity.MonitoredEndpoint
import com.github.vanyuart.endpointmonitoring.entity.MonitoringResult
import com.github.vanyuart.endpointmonitoring.entity.User

fun User.toDTO() = UserDto(
    username = username
)

fun MonitoredEndpoint.toDTO() = MonitoredEndpointDto(
    id = id,
    name = name,
    url = url,
    monitoringInterval = monitoringInterval,
    ownerId = owner.id,
    lastCheckDate = lastCheckDate,
    createdDate = createdDate,
    changedDate = changedDate,
)

fun MonitoringResult.toDTO() = MonitoringResultDto(
    statusCode = statusCode,
    payload = payload,
    dateOfCheck = dateOfCheck,
    endpointId = endpoint.id,
)
package com.github.vanyuart.endpointmonitoring.dto

data class UpdateMonitoredEndpointReq(
    val id: Long,
    val name: String? = null,
    val url: String? = null,
    val monitoringInterval: Int? = null,
)
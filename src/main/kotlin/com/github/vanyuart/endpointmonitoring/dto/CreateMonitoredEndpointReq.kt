package com.github.vanyuart.endpointmonitoring.dto

data class CreateMonitoredEndpointReq(
    val name: String,
    val url: String,
    val monitoringInterval: Int,
)
package com.github.vanyuart.endpointmonitoring.dto

data class CreateMonitorEndpointReq(
    val name: String,
    val url: String,
    val monitoringInterval: Int,
)
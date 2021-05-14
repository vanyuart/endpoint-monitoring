package com.github.vanyuart.endpointmonitoring.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive

data class UpdateMonitoredEndpointReq(
    val id: Long,

    @field:NotBlank(message = RequestValidation.ENDPOINT_NAME_MSG)
    val name: String? = null,

    @field:Pattern(message = RequestValidation.URL_PATTERN_MSG, regexp = RequestValidation.URL_PATTERN_REGEX)
    val url: String? = null,

    @field:Positive(message = RequestValidation.MONITORING_INTERVAL_MSG)
    val monitoringInterval: Int? = null,
)
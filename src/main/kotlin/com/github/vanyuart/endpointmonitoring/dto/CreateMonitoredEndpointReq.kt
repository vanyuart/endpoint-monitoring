package com.github.vanyuart.endpointmonitoring.dto

import com.github.vanyuart.endpointmonitoring.dto.RequestValidation.ENDPOINT_NAME_MSG
import com.github.vanyuart.endpointmonitoring.dto.RequestValidation.MONITORING_INTERVAL_MSG
import com.github.vanyuart.endpointmonitoring.dto.RequestValidation.URL_PATTERN_MSG
import com.github.vanyuart.endpointmonitoring.dto.RequestValidation.URL_PATTERN_REGEX
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive

data class CreateMonitoredEndpointReq(

    @field:NotBlank(message = ENDPOINT_NAME_MSG)
    val name: String,

    @field:Pattern(message = URL_PATTERN_MSG, regexp = URL_PATTERN_REGEX)
    val url: String,

    @field:Positive(message = MONITORING_INTERVAL_MSG)
    val monitoringInterval: Int,
)
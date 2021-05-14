package com.github.vanyuart.endpointmonitoring.dto

object RequestValidation {
    const val ID_MSG = "Entity Id should be a positive number."

    const val ENDPOINT_NAME_MSG = "Endpoint name should not be blank."

    const val URL_PATTERN_MSG = "URL does not have valid format, e.g. 'http(s)://example.com'."
    const val URL_PATTERN_REGEX = "(?:^|[ \\t])((https?:\\/\\/)(?:localhost|[\\w-]+(?:\\.[\\w-]+)+)(:\\d+)?(\\/\\S*)?)"

    const val MONITORING_INTERVAL_MSG = "Monitoring interval should be greater than zero."
}

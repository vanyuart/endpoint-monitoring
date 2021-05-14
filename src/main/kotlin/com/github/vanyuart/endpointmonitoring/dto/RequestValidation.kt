package com.github.vanyuart.endpointmonitoring.dto

object RequestValidation {
    const val ENDPOINT_NAME_MSG = "Endpoint name should not be blank."

    const val URL_PATTERN_MSG = "URL does not have valid format, e.g. 'http(s)://example.com'."
    const val URL_PATTERN_REGEX = "https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)"

    const val MONITORING_INTERVAL_MSG = "Monitoring interval should be greater than zero."
}

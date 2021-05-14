package com.github.vanyuart.endpointmonitoring.dto.type

data class FieldValidationErrorDto(
    private val fieldName: String,
    private val message: String,
)
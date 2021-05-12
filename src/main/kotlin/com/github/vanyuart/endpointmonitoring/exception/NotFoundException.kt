package com.github.vanyuart.endpointmonitoring.exception

/**
 * Resource not found exception
 */
class NotFoundException(message: String) : Exception(message) {
    companion object {
        fun create(resourceName: String, identifier: Any) =
            NotFoundException("$resourceName identified by $identifier not found.")
    }
}
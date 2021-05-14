package com.github.vanyuart.endpointmonitoring.exception

/**
 * Resource not found exception
 */
class NotAllowedException(message: String) : Exception(message) {
    companion object {
        fun create(resourceName: String?, identifier: Any) =
            NotAllowedException("Current user not allowed to access $resourceName identified by $identifier.")
    }
}
package com.github.vanyuart.endpointmonitoring.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.full.companionObject

/**
 * @sample{ private val log by logger() }
 */

fun <T : Any> T.logger(): Lazy<Logger> = logger(javaClass)

fun logger(forClass: Class<*>): Lazy<Logger> = lazy { getLogger(getClassForLogging(forClass)) }

fun getLogger(forClass: Class<*>): Logger = LoggerFactory.getLogger(forClass)

fun <T : Any> getClassForLogging(javaClass: Class<T>): Class<*> {
    return javaClass.enclosingClass?.takeIf {
        it.kotlin.companionObject?.java == javaClass
    } ?: javaClass
}
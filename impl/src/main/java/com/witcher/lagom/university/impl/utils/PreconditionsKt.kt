package com.witcher.lagom.university.impl.utils

import com.lightbend.lagom.javadsl.api.transport.BadRequest

fun checkStringNotEmpty(value: String?, param: String) = if (value.isNullOrBlank()) {
    throw BadRequest("Parameter $param must not be empty.")
} else value

fun <T> checkNotNullOrThrow(value: T?, param: String) = value ?: throw BadRequest("Parameter $param must not be empty.")

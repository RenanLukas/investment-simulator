package com.renanlukas.feature.core.domain

/**
 * return the unwrapped value of T or throw NullPointerException.
 */
inline fun <reified T> T?.getOrThrow(): T = this
    ?: throw NullPointerException("Required ${T::class} is null")

/**
 * return the unwrapped value of T or default.
 */
inline fun <reified T> T?.getOrDefault(default: T): T = this ?: default
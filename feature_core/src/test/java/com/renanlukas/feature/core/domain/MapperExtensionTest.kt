package com.renanlukas.feature.core.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class MapperExtensionTest {

    @Test
    fun `should get value instead of throwing if it's not null`() {
        assertEquals("value".getOrThrow(), "value")
    }

    @Test(expected = NullPointerException::class)
    fun `should throw if value it's null`() {
        (null as String?).getOrThrow()
    }

    @Test
    fun `should return value instead of default if it's not null`() {
        assertEquals(1.getOrDefault(0), 1)
    }

    @Test
    fun `should return default value if it's null`() {
        assertEquals((null as Int?).getOrDefault(0), 0)
    }
}
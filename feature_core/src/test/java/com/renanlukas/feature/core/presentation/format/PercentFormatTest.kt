package com.renanlukas.feature.core.presentation.format

import com.renanlukas.feature.core.presentation.LocaleProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.util.*

class PercentFormatTest {

    private val testLocale = Locale("pt", "BR")
    private val localeProvider: LocaleProvider = mockk()
    private val classToTest: PercentFormat = PercentFormat(localeProvider)

    @Before
    fun setup() {
        every { localeProvider.getLocale() } returns testLocale
    }

    @Test
    fun `should format percentage with valid number`() {
        assertEquals(classToTest.format(BigDecimal(5.0)), "5%")
    }

    @Test
    fun `should return default if exception is thrown`() {
        every { localeProvider.getLocale() } throws NumberFormatException()
        assertEquals(classToTest.format(BigDecimal(50)), PercentFormat.DEFAULT_FORMAT)
    }
}
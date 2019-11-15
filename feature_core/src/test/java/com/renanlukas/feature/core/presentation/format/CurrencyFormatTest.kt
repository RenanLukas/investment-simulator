package com.renanlukas.feature.core.presentation.format

import com.renanlukas.feature.core.presentation.LocaleProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.util.*

class CurrencyFormatTest {

    private val testLocale = Locale("pt", "BR")
    private val testCurrencyCode = Currency.getInstance(testLocale).getSymbol(testLocale)
    private val localeProvider: LocaleProvider = mockk()
    private val classToTest: CurrencyFormat = CurrencyFormat(localeProvider)

    @Before
    fun setup() {
        every { localeProvider.getLocale() } returns testLocale
    }

    @Test
    fun `should format currency with valid number`() {
        assertEquals(classToTest.format(BigDecimal(123.45)), "$testCurrencyCode 123,45")
    }

    @Test
    fun `should return default if exception is thrown`() {
        every { localeProvider.getLocale() } throws NumberFormatException()
        assertEquals(classToTest.format(BigDecimal(123.45)), CurrencyFormat.DEFAULT_FORMAT)
    }

    @Test
    fun `should return currency code`() {
        assertEquals(classToTest.currencyCode(), testCurrencyCode)
    }
}
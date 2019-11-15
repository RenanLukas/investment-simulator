package com.renanlukas.feature.core.presentation.format

import com.renanlukas.feature.core.presentation.LocaleProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class DateFormatTest {

    private val testLocale = Locale("pt", "BR")
    private val localeProvider: LocaleProvider = mockk()
    private val classToTest: DateFormat = DateFormat(localeProvider)

    @Before
    fun setup() {
        every { localeProvider.getLocale() } returns testLocale
    }

    @Test
    fun `should format date with valid timestamp and pattern`() {
        val expected = classToTest.format(1573753952000L, DateFormat.Pattern.DayMonthYearSlash)
        assertEquals(expected, "14/11/2019")
    }
}
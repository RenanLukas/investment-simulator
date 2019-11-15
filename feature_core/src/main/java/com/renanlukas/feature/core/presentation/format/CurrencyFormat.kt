package com.renanlukas.feature.core.presentation.format

import com.renanlukas.feature.core.presentation.LocaleProvider
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class CurrencyFormat @Inject constructor(private val localeProvider: LocaleProvider) {

    fun format(value: BigDecimal): String =
        try {
            NumberFormat.getCurrencyInstance(localeProvider.getLocale()).format(value)
        } catch (numberFormatException: NumberFormatException) {
            DEFAULT_FORMAT
        }

    fun currencyCode(): String =
        Currency.getInstance(localeProvider.getLocale()).getSymbol(localeProvider.getLocale())

    companion object {
        internal const val DEFAULT_FORMAT = ""
    }
}
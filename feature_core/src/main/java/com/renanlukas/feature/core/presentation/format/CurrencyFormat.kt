package com.renanlukas.feature.core.presentation.format

import com.renanlukas.feature.core.presentation.LocaleProvider
import java.math.BigDecimal
import java.text.NumberFormat
import javax.inject.Inject

class CurrencyFormat @Inject constructor(private val localeProvider: LocaleProvider) {

    fun format(value: BigDecimal): String =
        try {
            NumberFormat.getCurrencyInstance(localeProvider.getLocale()).format(value)
        } catch (throwable: Throwable) {
            DEFAULT_FORMAT
        }

    companion object {
        internal const val DEFAULT_FORMAT = "-"
    }
}
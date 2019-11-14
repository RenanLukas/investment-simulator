package com.renanlukas.feature.core.presentation.format

import com.renanlukas.feature.core.presentation.LocaleProvider
import java.math.BigDecimal
import java.text.NumberFormat
import javax.inject.Inject

class PercentFormat @Inject constructor(private val localeProvider: LocaleProvider) {

    fun format(value: BigDecimal): String =
        try {
            val percentageAmount = value / BigDecimal(100)
            NumberFormat.getPercentInstance(localeProvider.getLocale()).format(percentageAmount)
        } catch (throwable: Throwable) {
            DEFAULT_FORMAT
        }

    companion object {
        internal const val DEFAULT_FORMAT = "-"
    }
}
package com.renanlukas.feature.core.presentation.format

import com.renanlukas.feature.core.presentation.LocaleProvider
import java.math.BigDecimal
import java.text.NumberFormat
import javax.inject.Inject

class CurrencyFormat @Inject constructor(private val localeProvider: LocaleProvider) {

    fun format(value: BigDecimal) =
        NumberFormat.getNumberInstance(localeProvider.getLocale()).format(value)
}
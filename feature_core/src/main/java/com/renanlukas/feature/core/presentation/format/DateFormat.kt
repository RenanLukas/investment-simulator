package com.renanlukas.feature.core.presentation.format

import com.renanlukas.feature.core.presentation.LocaleProvider
import java.text.SimpleDateFormat
import javax.inject.Inject

class DateFormat @Inject constructor(
    private val localeProvider: LocaleProvider
) {

    fun format(timestamp: Long, pattern: Pattern): String =
        SimpleDateFormat(pattern.value, localeProvider.getLocale()).format(timestamp)

    sealed class Pattern(val value: String) {
        object DD_MM_YYYY : Pattern("dd/MM/yyyy")
    }
}
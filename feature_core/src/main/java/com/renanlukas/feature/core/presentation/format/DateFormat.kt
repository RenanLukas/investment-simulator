package com.renanlukas.feature.core.presentation.format

import java.text.SimpleDateFormat
import javax.inject.Inject

class DateFormat @Inject constructor() {

    fun format(timestamp: Long, pattern: Pattern): String =
        SimpleDateFormat(pattern.value).format(timestamp)

    sealed class Pattern(val value: String) {
        object DD_MM_YYYY : Pattern("dd/MM/yyyy")
    }
}
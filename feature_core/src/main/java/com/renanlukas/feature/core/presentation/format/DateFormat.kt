package com.renanlukas.feature.core.presentation.format

import android.annotation.SuppressLint
import com.renanlukas.feature.core.presentation.LocaleProvider
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateFormat @Inject constructor(
    private val localeProvider: LocaleProvider
) {

    fun format(timestamp: Long, pattern: Pattern): String =
        SimpleDateFormat(pattern.value, localeProvider.getLocale()).format(timestamp)

    fun format(day: Int, month: Int, year: Int, pattern: Pattern): String {
        val calendar = Calendar.getInstance().apply {
            set(year, month, day)
        }
        return SimpleDateFormat(pattern.value, localeProvider.getLocale()).format(calendar.time)
    }

    @SuppressLint("SimpleDateFormat")
    fun isValid(date: String, pattern: Pattern): Boolean {
        if (!date.matches(Regex(pattern.regex))) return false
        try {
            val simpleDateFormat = SimpleDateFormat(pattern.value).parse(date)
            return simpleDateFormat != null && !simpleDateFormat.before(Date())
        } catch (exception: Exception) {
            return false
        }
    }

    sealed class Pattern(val value: String, val regex: String) {
        object DayMonthYearSlash : Pattern("dd/MM/yyyy", "[0-9]{2}/[0-9]{2}/[0-9]{4}")
        object DayMonthYearDash : Pattern("dd-MM-yyyy", "[0-9]{2}-[0-9]{2}-[0-9]{4}")
    }
}
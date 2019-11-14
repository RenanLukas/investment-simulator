package com.renanlukas.feature.core.presentation

import java.util.*
import javax.inject.Inject

class LocaleProvider @Inject constructor() {

    fun getLocale(): Locale = Locale.getDefault()
}
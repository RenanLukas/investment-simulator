package com.renanlukas.feature.core.ui

import android.content.Context
import androidx.annotation.StringRes

sealed class Text {
    data class Resource(@StringRes val resource: Int) : Text()
    data class StringLiteral(val string: String) : Text()
}

fun Text.get(context: Context): String =
    when (this) {
        is Text.Resource -> context.getString(this.resource)
        is Text.StringLiteral -> this.string
    }
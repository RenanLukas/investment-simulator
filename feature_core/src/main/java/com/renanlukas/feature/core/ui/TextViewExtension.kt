package com.renanlukas.feature.core.ui

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


fun TextView.colorText(fullText: String, textToColor: String, @ColorRes color: Int) {
    val spannable = SpannableString(text)
    val indexTextToColor = text.indexOf(textToColor)
    spannable.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, color)),
        indexTextToColor,
        indexTextToColor + textToColor.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    setText(spannable, TextView.BufferType.SPANNABLE)
}
package com.renanlukas.feature.core.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.StringRes
import com.renanlukas.investmentsimulator.feature.core.R
import kotlinx.android.synthetic.main.view_description_value.view.*

class DescriptionValueView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_description_value, this)
    }

    fun bind(entity: Entity) {
        with(entity) {
            descriptionText.text = context.getString(description)
            valueText.text = value
        }
    }

    data class Entity(@StringRes val description: Int, val value: String)
}
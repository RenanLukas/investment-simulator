package com.renanlukas.feature.core.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.renanlukas.investmentsimulator.feature.core.R
import kotlinx.android.synthetic.main.view_main_button.view.*

class MainButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.view_main_button, this)
    }

    fun bind(entity: Entity, clicked: () -> Unit) {
        with(mainButton) {
            text = entity.value
            setOnClickListener { clicked() }
        }
    }

    data class Entity(val value: String)
}
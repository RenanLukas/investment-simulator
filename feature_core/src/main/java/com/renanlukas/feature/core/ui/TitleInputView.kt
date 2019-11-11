package com.renanlukas.feature.core.ui

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.FrameLayout
import com.renanlukas.investmentsimulator.feature.core.R
import kotlinx.android.synthetic.main.view_title_input.view.*

class TitleInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_title_input, this)
    }

    fun bind(entity: Entity) {
        bindTitle(entity)
        bindInput(entity)
    }

    private fun bindTitle(entity: Entity) {
        with(entity) {
            val titleValue = titleValue + if (isMandatory) MANDATORY_STRING else ""
            title.text = titleValue
        }
    }

    private fun bindInput(entity: Entity) {
        with(entity) {
            input.hint = hintValue
            input.inputType = inputType
        }
    }

    data class Entity(
        val titleValue: String,
        val hintValue: String,
        val inputType: Int = InputType.TYPE_CLASS_NUMBER,
        val isMandatory: Boolean = false
    )
}

private const val MANDATORY_STRING = " *"
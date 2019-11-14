package com.renanlukas.feature.core.ui

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.renanlukas.investmentsimulator.feature.core.R
import kotlinx.android.synthetic.main.view_title_input.view.*


class TitleInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var titleTextColor: Int = 0
    private var inputHint: String = ""

    init {
        inflate(context, R.layout.view_title_input, this)
        input.setOnFocusChangeListener { _, hasFocus ->
            input.hint = if (hasFocus) "" else inputHint
        }
    }

    fun bind(entity: Entity) {
        bindTitle(entity)
        bindInput(entity)
    }

    fun showError() {
        title.setTextColor(ContextCompat.getColor(context, R.color.error))
    }

    fun hideError() {
        title.setTextColor(ContextCompat.getColor(context, titleTextColor))
    }

    override fun onDetachedFromWindow() {
        input.onFocusChangeListener = null
        super.onDetachedFromWindow()
    }

    private fun bindTitle(entity: Entity) {
        with(entity) {
            val titleValue =
                context.getString(titleValue) + if (isMandatory) MANDATORY_STRING else ""
            title.text = titleValue
            titleTextColor = title.currentTextColor
        }
    }

    private fun bindInput(entity: Entity) {
        with(entity) {
            input.hint = context.getString(hintValue)
            input.inputType = inputType
            inputHint = input.hint.toString()
        }
    }

    data class Entity(
        @StringRes val titleValue: Int,
        @StringRes val hintValue: Int,
        val inputType: Int,
        val isMandatory: Boolean = false
    )
}

private const val MANDATORY_STRING = " *"
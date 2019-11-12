package com.renanlukas.feature.core.ui

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.renanlukas.investmentsimulator.feature.core.R
import kotlinx.android.synthetic.main.view_title_input.view.*

class TitleInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_title_input, this)
    }

    fun bind(entity: Entity) {
        bindTitle(entity)
        bindInput(entity)
    }

    fun showError() {

    }

    fun hideError() {

    }

    private fun bindTitle(entity: Entity) {
        with(entity) {
            val titleValue = context.getString(titleValue) + if (isMandatory) MANDATORY_STRING else ""
            title.text = titleValue
        }
    }

    private fun bindInput(entity: Entity) {
        with(entity) {
            input.hint = context.getString(hintValue)
            input.inputType = inputType
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
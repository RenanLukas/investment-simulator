package com.renanlukas.feature.core.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.renanlukas.investmentsimulator.feature.core.R
import kotlinx.android.synthetic.main.view_title_input.view.*


class TitleInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var inputHint: String = ""
    private var textChangedListener: TextWatcher? = null

    private var currentInputLength = 0

    init {
        inflate(context, R.layout.view_title_input, this)
        input.setOnFocusChangeListener { _, hasFocus ->
            input.hint = if (hasFocus) "" else inputHint
        }
    }

    fun bindInputValue(value: String) {
        if (textChangedListener != null) input.removeTextChangedListener(textChangedListener)
        input.setText(value)
        input.setSelection(value.length)
        currentInputLength = value.length
        if (textChangedListener != null) input.addTextChangedListener(textChangedListener)
    }

    fun bind(entity: Entity) {
        bindTitle(entity)
        bindInput(entity)
    }

    fun inputText() = input.text.toString()

    fun addTextChangedListener(callback: (String, Boolean) -> Unit) {
        textChangedListener =
            object : TextWatcher {
                override fun afterTextChanged(editable: Editable?) {
                    callback(editable.toString(), editable.toString().length < currentInputLength)
                }

                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            }
        input.addTextChangedListener(textChangedListener)
    }

    fun showError() {
        title.setTextColor(ContextCompat.getColor(context, R.color.error))
    }

    fun hideError() {
        title.setTextColor(ContextCompat.getColor(context, R.color.textDefault))
    }

    override fun onDetachedFromWindow() {
        input.onFocusChangeListener = null
        textChangedListener = null
        super.onDetachedFromWindow()
    }

    private fun bindTitle(entity: Entity) {
        with(entity) {
            val titleValue = titleValue.get(context) + if (isMandatory) MANDATORY_STRING else ""
            title.text = titleValue
        }
    }

    private fun bindInput(entity: Entity) {
        with(entity) {
            input.hint = hintValue.get(context)
            input.inputType = inputType
            inputHint = input.hint.toString()
        }
    }

    data class Entity(
        val titleValue: Text,
        val hintValue: Text,
        val inputType: Int,
        val isMandatory: Boolean = false
    )
}

private const val MANDATORY_STRING = " *"
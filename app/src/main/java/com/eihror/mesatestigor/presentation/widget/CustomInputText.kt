package com.eihror.mesatestigor.presentation.widget

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.eihror.mesatestigor.R
import com.eihror.mesatestigor.extensions.onDone

class CustomInputText(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private var label: AppCompatTextView
    private var text: AppCompatEditText

    init {
        View.inflate(context, R.layout.component_input_text, this)

        label = findViewById(R.id.text_view_label)
        text = findViewById(R.id.edit_text_value)
    }

    fun setLabel(value: Int) {
        label.setText(value)
    }

    fun setImeOptions(value: Int) {
        text.imeOptions = value
    }

    fun onTextChangedListener(listener: (String) -> Unit) {
        text.doOnTextChanged { text, start, before, count ->
            listener.invoke(text.toString())
        }
    }

    fun setInputType(value: Int) {
        text.inputType = value
    }

    fun isPassword(enable: Boolean) {
        if (enable) {
            text.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    fun onActionDoneListener(onActionDone: () -> Unit) {
        text.onDone(onActionDone)
    }

    fun getText(): String = text.text.toString()

}
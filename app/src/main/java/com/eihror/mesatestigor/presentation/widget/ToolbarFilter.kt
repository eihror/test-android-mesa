package com.eihror.mesatestigor.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.eihror.mesatestigor.R
import com.eihror.mesatestigor.extensions.setStatusBarPadding
import com.eihror.mesatestigor.extensions.setVisibilityInvisibleForBoolean

class ToolbarFilter(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private lateinit var buttonStart: AppCompatImageButton
    private lateinit var buttonEnd: AppCompatTextView
    private lateinit var title: AppCompatTextView

    init {
        View.inflate(context, R.layout.component_toolbar_filter, this)

        setupBiding()

        setStatusBarPadding()
    }

    private fun setupBiding() {
        buttonStart = findViewById(R.id.button_start)
        buttonEnd = findViewById(R.id.button_end)
        title = findViewById(R.id.text_view_toolbar_title)
    }

    fun setTitle(value: Int) {
        title.setText(value)
    }

    fun toggleButtonStartVisibility(show: Boolean) {
        buttonStart.setVisibilityInvisibleForBoolean(show)
    }

    fun setButtonStartAction(listener: (View) -> Unit) {
        buttonStart.setOnClickListener(listener)
    }

    fun setButtonStartIcon(value: Int) {
        buttonStart.setImageResource(value)
    }

    fun toggleButtonEndVisibility(show: Boolean) {
        buttonEnd.setVisibilityInvisibleForBoolean(show)
    }

    fun setButtonEndAction(listener: (View) -> Unit) {
        buttonEnd.setOnClickListener(listener)
    }

    fun setButtonEndText(value: Int) {
        buttonEnd.setText(value)
    }

}
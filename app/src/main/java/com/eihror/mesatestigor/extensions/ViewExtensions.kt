package com.eihror.mesatestigor.extensions

import android.view.View
import com.eihror.mesatestigor.R

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.setVisibilityGoneForBoolean(visible: Boolean) {
    if (visible) makeVisible() else makeGone()
}

fun View.setVisibilityInvisibleForBoolean(visible: Boolean) {
    if (visible) makeVisible() else makeInvisible()
}

fun View.setStatusBarPadding() {
    var statusBarHeight = 0
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
    }

    this.setPadding(
        paddingLeft,
        if (statusBarHeight > dpToPixels(24F)) {
            paddingTop + statusBarHeight
        } else {
            paddingTop + context.resources.getDimensionPixelSize(R.dimen.toolkit_dimen24)
        },
        paddingRight,
        paddingBottom
    )
}

fun View.enable(
    boolean: Boolean,
    applyAlpha: Boolean = true
) {
    apply {
        isEnabled = boolean
        isClickable = boolean
    }

    if (applyAlpha) {
        alpha = if (boolean) {
            1f
        } else {
            0.1f
        }
    }
}
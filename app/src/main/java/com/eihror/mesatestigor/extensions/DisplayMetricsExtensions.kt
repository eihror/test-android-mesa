package com.eihror.mesatestigor.extensions

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.Fragment

fun View.dpToPixels(dp: Float) = context.dpToPixels(dp)
fun Fragment.dpToPixels(dp: Float) = context?.dpToPixels(dp)
fun Fragment.dpToPixels(dp: Int) = requireContext().dpToPixels(dp)
fun Context.dpToPixels(dp: Float): Float {
    val metrics = Resources.getSystem().displayMetrics
    val px = dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    return Math.round(px).toFloat()
}

fun Context.dpToPixels(dp: Int): Int {
    val metrics = Resources.getSystem().displayMetrics
    return dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

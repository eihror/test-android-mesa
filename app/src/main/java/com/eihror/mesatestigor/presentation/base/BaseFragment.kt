package com.eihror.mesatestigor.presentation.base

import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.eihror.mesatestigor.R

open class BaseFragment(layout: Int) : Fragment(layout) {
    protected fun setTransparentStatusBar() {
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        activity?.window?.statusBarColor =
            ContextCompat.getColor(activity?.applicationContext!!, R.color.status_bar)
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = activity?.window
        val winParams = win?.attributes

        winParams?.let {
            if (on) {
                it.flags = it.flags or bits
            } else {
                it.flags = it.flags and bits.inv()
            }
            win.attributes = winParams
        }
    }
}
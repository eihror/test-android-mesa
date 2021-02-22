package com.eihror.mesatestigor.presentation.home

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eihror.mesatestigor.R
import com.eihror.mesatestigor.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_filter.*

class FilterFragment : BaseFragment(R.layout.fragment_filter) {

    private lateinit var navController: NavController

    private val toolbar get() = toolbar_filter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setTransparentStatusBar()

        setupView()
    }

    private fun setupView() {
        toolbar.apply {
            setTitle(R.string.main_nav_graph_filter_toolbar_title)
            setButtonStartIcon(R.drawable.ic_back_blue)
            setButtonStartAction(::onButtonStartAction)
            setButtonEndText(R.string.main_nav_graph_filter_toolbar_end_text)
            setButtonEndAction(::onButtonEndAction)
        }
    }

    private fun onButtonStartAction(view: View) {
        navController.popBackStack()
    }

    private fun onButtonEndAction(view: View) {
        navController.popBackStack()
    }

}
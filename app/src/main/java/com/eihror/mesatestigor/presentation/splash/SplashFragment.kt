package com.eihror.mesatestigor.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eihror.mesatestigor.R
import com.eihror.mesatestigor.extensions.setStatusBarPadding
import com.eihror.mesatestigor.presentation.base.BaseFragment

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private lateinit var navController: NavController
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel = ViewModelProvider(this).get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setTransparentStatusBar()

        setupView()
        setupObservable()
    }

    private fun setupView() {
        requireView().setStatusBarPadding()

        Handler().postDelayed({
            splashViewModel.verifyToken()
        }, 3000)
    }

    private fun setupObservable() {
        splashViewModel.proceedToNextScreen().observe(viewLifecycleOwner, Observer {
            val action = when (it) {
                FLOW.FLOW_AUTH -> SplashFragmentDirections.actionSplashFragmentToAuthenticationFragment()
                else -> SplashFragmentDirections.actionSplashFragmentToHomeFragment()
            }
            navController.navigate(action)
        })
    }
}
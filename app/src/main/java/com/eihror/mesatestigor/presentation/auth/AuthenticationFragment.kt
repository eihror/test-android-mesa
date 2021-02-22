package com.eihror.mesatestigor.presentation.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eihror.mesatestigor.R
import com.eihror.mesatestigor.extensions.setStatusBarPadding
import com.eihror.mesatestigor.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_authentication.*

class AuthenticationFragment : BaseFragment(R.layout.fragment_authentication) {

    private lateinit var navController: NavController

    private val signIn get() = button_sign_in
    private val signUp get() = text_view_sign_up

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setTransparentStatusBar()
        requireView().setStatusBarPadding()

        setupView()
    }

    private fun setupView() {
        signIn.setOnClickListener {
            val action =
                AuthenticationFragmentDirections.actionAuthenticationFragmentToSignInFragment()
            navController.navigate(action)
        }

        signUp.setOnClickListener {
            val action =
                AuthenticationFragmentDirections.actionAuthenticationFragmentToSignUpFragment()
            navController.navigate(action)
        }
    }

}
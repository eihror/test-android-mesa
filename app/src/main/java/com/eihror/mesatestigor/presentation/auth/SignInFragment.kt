package com.eihror.mesatestigor.presentation.auth

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eihror.mesatestigor.R
import com.eihror.mesatestigor.extensions.enable
import com.eihror.mesatestigor.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_signin.*

class SignInFragment : BaseFragment(R.layout.fragment_signin) {

    private lateinit var navController: NavController

    private val toolbar get() = toolbar_sign_in
    private val email get() = input_email
    private val password get() = input_password
    private val login get() = button_login
    private val signUp get() = text_view_sign_up

    private lateinit var signInViewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInViewModel = ViewModelProvider(this).get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setTransparentStatusBar()

        setupView()
        setupActions()
        setupObservable()
    }

    private fun setupView() {
        login.enable(false)

        toolbar.apply {
            setButtonStartIcon(R.drawable.ic_close)
            setButtonStartAction(::onButtonStartAction)
            setTitle(R.string.main_nav_graph_sign_in_toolbar_title)
        }

        email.apply {
            setLabel(R.string.main_nav_graph_sign_in_email_label)
            setImeOptions(EditorInfo.IME_ACTION_NEXT)
            setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
            onTextChangedListener {
                signInViewModel.setEmail(it)
            }
        }

        password.apply {
            setLabel(R.string.main_nav_graph_sign_in_password_label)
            setImeOptions(EditorInfo.IME_ACTION_DONE)
            setInputType(InputType.TYPE_CLASS_TEXT)
            onActionDoneListener(::onActionDoneListener)
            isPassword(true)
            onTextChangedListener {
                signInViewModel.setPassword(it)
            }
        }
    }

    private fun setupActions() {
        login.setOnClickListener {
            signInViewModel.doLogin()
        }

        signUp.setOnClickListener {
            val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            navController.navigate(action)
        }
    }

    private fun setupObservable() {
        signInViewModel.allFieldsAreValid().observe(viewLifecycleOwner, Observer {
            login.enable(it)
        })

        signInViewModel.canProceedToNextScreen().observe(viewLifecycleOwner, Observer {
            if (it) {
                val action = SignInFragmentDirections.actionSignInFragmentToHomeFragment()
                navController.navigate(action)
            }
        })
    }

    private fun onActionDoneListener() {
        login.callOnClick()
    }

    private fun onButtonStartAction(view: View) {
        navController.popBackStack()
    }
}
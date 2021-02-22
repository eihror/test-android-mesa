package com.eihror.mesatestigor.presentation.auth

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eihror.mesatestigor.R
import com.eihror.mesatestigor.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment : BaseFragment(R.layout.fragment_signup) {

    private lateinit var navController: NavController
    private val toolbar get() = toolbar_sign_up
    private val name get() = input_name
    private val email get() = input_email
    private val password get() = input_password
    private val confirmPassword get() = input_confirm_password
    private val birthDay get() = input_birth_day
    private val singUp get() = button_create

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setTransparentStatusBar()

        setupView()
        setupActions()
    }

    private fun setupView() {
        toolbar.apply {
            setButtonStartIcon(R.drawable.ic_close)
            setButtonStartAction(::onButtonStartAction)
            setTitle(R.string.main_nav_graph_sign_up_toolbar_title)
        }

        name.apply {
            setLabel(R.string.main_nav_graph_sign_up_name_label)
            setImeOptions(EditorInfo.IME_ACTION_NEXT)
            setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
        }

        email.apply {
            setLabel(R.string.main_nav_graph_sign_up_email_label)
            setImeOptions(EditorInfo.IME_ACTION_NEXT)
            setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
        }

        password.apply {
            setLabel(R.string.main_nav_graph_sign_up_password_label)
            setImeOptions(EditorInfo.IME_ACTION_NEXT)
            setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }

        confirmPassword.apply {
            setLabel(R.string.main_nav_graph_sign_up_confirm_password_label)
            setImeOptions(EditorInfo.IME_ACTION_DONE)
            setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
            onActionDoneListener(::onActionDoneListener)
        }

        birthDay.apply {
            setLabel(R.string.main_nav_graph_sign_up_birth_day_label)
            setImeOptions(EditorInfo.IME_ACTION_DONE)
            setInputType(InputType.TYPE_DATETIME_VARIATION_DATE)
            onActionDoneListener(::onActionDoneListener)
        }
    }

    private fun setupActions() {
        singUp.setOnClickListener {

        }
    }

    private fun onActionDoneListener() {
        singUp.callOnClick()
    }

    private fun onButtonStartAction(view: View) {
        navController.popBackStack()
    }
}
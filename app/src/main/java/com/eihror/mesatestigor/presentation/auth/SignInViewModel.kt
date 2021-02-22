package com.eihror.mesatestigor.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eihror.mesatestigor.extensions.isValidEmail
import com.eihror.mesatestigor.extensions.isValidPassword
import com.eihror.mesatestigor.presentation.base.JobViewModel
import com.eihror.mesatestigor.providers.repository.AuthenticationRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class SignInViewModel : JobViewModel(), KoinComponent {

    private val repository: AuthenticationRepository by inject()
    private val isValid: MutableLiveData<Boolean> = MutableLiveData()
    private val proceed: MutableLiveData<Boolean> = MutableLiveData()

    private var email: String = ""
    private var password: String = ""

    fun setEmail(value: String) {
        email = value
        validateFields()
    }

    fun setPassword(value: String) {
        password = value
        validateFields()
    }

    fun allFieldsAreValid(): LiveData<Boolean> = isValid

    fun canProceedToNextScreen(): LiveData<Boolean> = proceed

    private fun validateFields() {
        val isValidEmail = email.isValidEmail()
        val isValidPassword = password.isValidPassword()

        isValid.postValue(isValidEmail && isValidPassword)
    }

    fun doLogin() {
        bgScope.launch {
            try {
                loading.postValue(true)
                repository.signIn(email, password)
                proceed.postValue(true)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                loading.postValue(false)
            }
        }
    }

}
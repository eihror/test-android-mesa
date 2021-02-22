package com.eihror.mesatestigor.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eihror.mesatestigor.presentation.base.JobViewModel
import com.eihror.mesatestigor.providers.api.shared_preferences.PreferencesManager
import kotlinx.coroutines.launch

enum class FLOW {
    FLOW_HOME,
    FLOW_AUTH
}

class SplashViewModel : JobViewModel() {

    private val proceed: MutableLiveData<FLOW> = MutableLiveData()

    fun proceedToNextScreen(): LiveData<FLOW> = proceed

    fun verifyToken() {
        bgScope.launch {
            try {
                proceed.postValue(if (PreferencesManager.hasToken()) FLOW.FLOW_HOME else FLOW.FLOW_AUTH)
            } catch (e: Exception) {
                // Fail Silently
            }
        }
    }
}
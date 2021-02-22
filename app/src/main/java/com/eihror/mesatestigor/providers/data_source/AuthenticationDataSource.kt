package com.eihror.mesatestigor.providers.data_source

import com.eihror.mesatestigor.providers.api.TestApi
import com.eihror.mesatestigor.providers.api.shared_preferences.PreferencesManager
import com.eihror.mesatestigor.providers.models.request.SignInRequest
import com.eihror.mesatestigor.providers.models.request.SignUpRequest
import com.eihror.mesatestigor.providers.repository.AuthenticationRepository

class AuthenticationDataSource(private val api: TestApi) : AuthenticationRepository {
    override suspend fun signIn(email: String, password: String) {
        try {
            val request = SignInRequest(email, password)
            val result = api.signIn(request).await()

            if (result.token != null) {
                PreferencesManager.authenticationToken = result.token
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun signUp(name: String, email: String, password: String) {
        try {
            val request = SignUpRequest(name, email, password)
            val result = api.signUp(request).await()

            if (result.token != null) {
                PreferencesManager.authenticationToken = result.token
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
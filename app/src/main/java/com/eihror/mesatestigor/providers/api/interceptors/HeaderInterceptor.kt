package com.eihror.mesatestigor.providers.api.interceptors

import com.eihror.mesatestigor.providers.api.shared_preferences.PreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    companion object {
        private const val CONTENT_TYPE_HEADER = "Content-Type"
        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = PreferencesManager.authenticationToken
        val builder = chain.request().newBuilder().apply {
            addHeader(CONTENT_TYPE_HEADER, "application/json")
            if (token.isNotEmpty()) {
                addHeader(AUTHORIZATION_HEADER, "Bearer $token")
            }
        }

        return chain.proceed(builder.build())
    }

}
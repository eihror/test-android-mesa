package com.eihror.mesatestigor.providers.repository

interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(name: String, email: String, password: String)
}
package com.eihror.mesatestigor.providers.api.shared_preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

object PreferencesManager {

    private const val SHARED_PREFERENCES_NAME = "MesaTestPreferences"

    private lateinit var preferences: SharedPreferences

    private const val TOKEN: String = "TOKEN"

    var authenticationToken: String
        get() = preferences.getString(TOKEN)
        set(newValue) = preferences.putString(TOKEN, newValue)

    fun hasToken(): Boolean {
        return authenticationToken.isNotEmpty()
    }

    fun Application.setupLocalStorage() {
        preferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

}

private fun SharedPreferences.putString(key: String?, value: String?) {
    if (key.isNullOrEmpty() || value.isNullOrEmpty()) return
    edit().putString(key, value).apply()
}

private fun SharedPreferences.putStringUnsafe(key: String?, value: String?) {
    if (key.isNullOrEmpty() || value == null) return
    edit().putString(key, value).apply()
}

private fun SharedPreferences.getString(key: String?): String {
    return if (key.isNullOrEmpty() || contains(key).not()) ""
    else getString(key, "") ?: ""
}

private fun SharedPreferences.putBoolean(key: String?, value: Boolean) {
    edit().putBoolean(key, value).apply()
}

private fun SharedPreferences.getBoolean(key: String?): Boolean {
    return getBoolean(key, false)
}

private fun SharedPreferences.putLong(key: String?, value: Long) {
    edit().putLong(key, value).apply()
}

private fun SharedPreferences.putInt(key: String?, value: Int) {
    edit().putInt(key, value).apply()
}

private fun SharedPreferences.getLong(key: String?): Long {
    return getLong(key, 0)
}
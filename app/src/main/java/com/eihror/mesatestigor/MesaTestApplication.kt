package com.eihror.mesatestigor

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.eihror.mesatestigor.providers.api.shared_preferences.PreferencesManager.setupLocalStorage
import com.eihror.mesatestigor.providers.di.PROPERTY_BASE_URL
import com.eihror.mesatestigor.providers.di.androidModule
import com.eihror.mesatestigor.providers.di.dataSourceModule
import com.eihror.mesatestigor.providers.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MesaTestApplication : Application() {
    private val TAG = MesaTestApplication::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        context = baseContext
        setupLocalStorage()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            printLogger(level = Level.INFO)
            androidContext(this@MesaTestApplication)
            modules(
                listOf(
                    androidModule,
                    networkModule,
                    dataSourceModule
                )
            )
            properties(
                mapOf(
                    PROPERTY_BASE_URL to "https://mesa-news-api.herokuapp.com/"
                )
            )
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context

        fun getApplicationContext(): Context {
            return context
        }
    }
}
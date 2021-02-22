package com.eihror.mesatestigor.providers.di

import com.eihror.mesatestigor.BuildConfig
import com.eihror.mesatestigor.providers.api.TestApi
import com.eihror.mesatestigor.providers.api.interceptors.HeaderInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

const val PROPERTY_BASE_URL = "baseUrl"
var logInterceptor = StringQualifier("logInterceptor")
var headerInterceptor = StringQualifier("headerInterceptor")
var httpClient = StringQualifier("httpClient")
var retrofit = StringQualifier("retrofit")
var api = StringQualifier("api")
private const val TIMEOUT = 60L

val networkModule = module {
    single(logInterceptor) {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        } as Interceptor
    }

    /* region Base API */
    single(headerInterceptor) { HeaderInterceptor() }

    single(httpClient) {
        OkHttpClient
            .Builder()
            .addInterceptor(get(logInterceptor))
            .addInterceptor(get(headerInterceptor))
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    single(retrofit) {
        val baseUrl = getProperty<String>(PROPERTY_BASE_URL)

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get(httpClient))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addConverterFactory(UnitConverterFactory)
            .build()
    }

    single(api) {
        val retrofit: Retrofit = get(retrofit)
        retrofit.create(TestApi::class.java)
    }
    /* endregion Base API */

}

object UnitConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (type == Unit::class.java) UnitConverter else null
    }

    private object UnitConverter : Converter<ResponseBody, Unit> {
        override fun convert(value: ResponseBody) {
            value.close()
        }
    }
}
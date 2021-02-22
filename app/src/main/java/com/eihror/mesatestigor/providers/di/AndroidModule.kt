package com.eihror.mesatestigor.providers.di

import com.eihror.mesatestigor.providers.api.json_adapters.DateJsonAdapter
import com.squareup.moshi.Moshi
import org.koin.dsl.module

val androidModule = module {
    single {
        Moshi.Builder()
            .add(DateJsonAdapter)
            .build()
    }
}
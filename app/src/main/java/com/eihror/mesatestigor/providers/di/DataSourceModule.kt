package com.eihror.mesatestigor.providers.di

import com.eihror.mesatestigor.providers.data_source.AuthenticationDataSource
import com.eihror.mesatestigor.providers.data_source.NewsDataSource
import com.eihror.mesatestigor.providers.repository.AuthenticationRepository
import com.eihror.mesatestigor.providers.repository.NewsRepository
import org.koin.dsl.module

val dataSourceModule = module {
    single<AuthenticationRepository> { AuthenticationDataSource(get(api)) }
    single<NewsRepository> { NewsDataSource(get(api)) }
}
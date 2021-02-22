package com.eihror.mesatestigor.providers.repository

import com.eihror.mesatestigor.providers.models.response.News

interface NewsRepository {
    suspend fun fetchNews(restart: Boolean = false, date: String = ""): List<News>
}
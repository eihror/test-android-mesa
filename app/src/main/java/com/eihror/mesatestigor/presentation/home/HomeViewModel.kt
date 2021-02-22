package com.eihror.mesatestigor.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eihror.mesatestigor.presentation.base.JobViewModel
import com.eihror.mesatestigor.providers.models.response.News
import com.eihror.mesatestigor.providers.repository.NewsRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : JobViewModel(), KoinComponent {

    private val repository: NewsRepository by inject()

    private val newsListFounded: MutableLiveData<List<News>> = MutableLiveData()

    fun newsListFounded(): LiveData<List<News>> = newsListFounded

    fun fetchNews(restart: Boolean = false) {
        bgScope.launch {
            try {
                val result = repository.fetchNews(restart)
                newsListFounded.postValue(result)
            } catch (e: Exception) {
                // Fails Silently
                e.printStackTrace()
            }
        }
    }

}
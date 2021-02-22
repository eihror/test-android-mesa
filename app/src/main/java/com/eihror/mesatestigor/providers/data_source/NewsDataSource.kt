package com.eihror.mesatestigor.providers.data_source

import com.eihror.mesatestigor.providers.api.TestApi
import com.eihror.mesatestigor.providers.models.response.News
import com.eihror.mesatestigor.providers.models.response.PaginationResponse
import com.eihror.mesatestigor.providers.repository.NewsRepository

class NewsDataSource(private val api: TestApi) : NewsRepository {

    private var pagination = PaginationResponse()

    private fun setPagination(value: PaginationResponse) {
        pagination = value
    }

    private fun clearPagination() {
        pagination = PaginationResponse()
    }

    private val totalList: MutableList<News> = mutableListOf()

    override suspend fun fetchNews(restart: Boolean, date: String): List<News> {
        try {

            if (restart) {
                totalList.clear()
                clearPagination()
            }

            val nextPage: Int = pagination.currentPage.let {
                if (it != 0) {
                    it + 1
                } else {
                    1
                }
            }

            if (nextPage == 1 || nextPage <= pagination.totalPages) {
                val response =
                    api.fetchNews(nextPage, pagination.perPage, date).await()
                setPagination(response.pagination)

                totalList.addAll(response.data)

                return totalList
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            throw e
        }
    }

}
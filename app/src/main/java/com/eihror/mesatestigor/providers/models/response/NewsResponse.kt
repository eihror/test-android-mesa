package com.eihror.mesatestigor.providers.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "pagination") val pagination: PaginationResponse,
    @Json(name = "data") val data: List<News> = emptyList()
)

@JsonClass(generateAdapter = true)
data class PaginationResponse(
    @Json(name = "current_page") val currentPage: Int = 0,
    @Json(name = "per_page") val perPage: Int = 20,
    @Json(name = "total_pages") val totalPages: Int = 0,
    @Json(name = "total_items") val totalItems: Int = 0
)

@JsonClass(generateAdapter = true)
data class News(
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "content") val content: String,
    @Json(name = "author") val author: String,
    @Json(name = "published_at") val publishedAt: Date?,
    @Json(name = "highlight") val isHighlight: Boolean,
    @Json(name = "url") val url: String,
    @Json(name = "image_url") val imageUrl: String
)
package com.eihror.mesatestigor.providers.api

import com.eihror.mesatestigor.providers.models.request.SignInRequest
import com.eihror.mesatestigor.providers.models.request.SignUpRequest
import com.eihror.mesatestigor.providers.models.response.News
import com.eihror.mesatestigor.providers.models.response.NewsResponse
import com.eihror.mesatestigor.providers.models.response.TokenResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TestApi {

    @POST("v1/client/auth/signin")
    fun signIn(@Body request: SignInRequest): Deferred<TokenResponse>

    @POST("v1/client/auth/signup")
    fun signUp(@Body request: SignUpRequest): Deferred<TokenResponse>

    @GET("v1/client/news")
    fun fetchNews(
        @Query("current_page") currentPage: Int,
        @Query("per_page") perPage: Int,
        @Query("published_at") publishedAt: String
    ): Deferred<NewsResponse>

    @GET("v1/client/news/highlights")
    fun fetchHightlights(): Deferred<List<News>>

}
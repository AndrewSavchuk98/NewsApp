package com.example.savchuk.newsappmvvm.data.remote


import com.example.savchuk.newsappmvvm.BuildConfig
import com.example.savchuk.newsappmvvm.data.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "ru",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey : String = BuildConfig.API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey : String = BuildConfig.API_KEY
    ) : Response<NewsResponse>


}
package com.roshanadke.dashboard.data.network

import com.roshanadke.dashboard.data.dto.NewsMainListDto
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {

    companion object {
        val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("everything")
    suspend fun getNewsList(
        @Query("q") query: String,
        @Query("pageSize") pageSize: String,
        @Query("page") pageNumber: String,
    ): NewsMainListDto

}
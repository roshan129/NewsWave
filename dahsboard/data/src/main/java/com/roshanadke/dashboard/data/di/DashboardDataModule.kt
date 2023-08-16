package com.roshanadke.dashboard.data.di

import com.roshanadke.dashboard.data.network.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DashboardDataModule {


    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(NewsApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

}
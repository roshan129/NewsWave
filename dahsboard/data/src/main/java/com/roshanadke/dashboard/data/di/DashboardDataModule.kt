package com.roshanadke.dashboard.data.di

import com.google.gson.GsonBuilder
import com.roshanadke.dashboard.data.BuildConfig
import com.roshanadke.dashboard.data.network.NewsApiService
import com.roshanadke.dashboard.data.repository.NewsDashboardRepositoryImpl
import com.roshanadke.dashboard.domain.repository.NewsDashboardRepository
import com.roshanadke.dashboard.domain.use_case.GetNewsDashboardListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DashboardDataModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Authorization", BuildConfig.API_KEY)
                    .method(original.method, original.body)
                    .build()

                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(NewsApiService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsDashboardRepository(apiService: NewsApiService): NewsDashboardRepository {
        return NewsDashboardRepositoryImpl(apiService)
    }


}
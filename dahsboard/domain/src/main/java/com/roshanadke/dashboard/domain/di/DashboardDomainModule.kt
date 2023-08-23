package com.roshanadke.dashboard.domain.di

import com.roshanadke.dashboard.domain.repository.NewsDashboardRepository
import com.roshanadke.dashboard.domain.use_case.GetNewsDashboardListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DashboardDomainModule {

    @Provides
    @Singleton
    fun provideGetNewsDashboardListUseCase(
        newsDashboardRepository: NewsDashboardRepository
    ): GetNewsDashboardListUseCase {
        return GetNewsDashboardListUseCase(newsDashboardRepository)
    }




}
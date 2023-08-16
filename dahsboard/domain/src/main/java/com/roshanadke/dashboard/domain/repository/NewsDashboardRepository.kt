package com.roshanadke.dashboard.domain.repository

import com.roshanadke.dashboard.data.network.NewsApiService
import kotlinx.coroutines.flow.Flow

interface NewsDashboardRepository {

    fun getNewsDashboardMainList(): Flow<>
}
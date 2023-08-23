package com.roshanadke.dashboard.domain.repository

import com.roshanadke.common.utils.Resource
import com.roshanadke.dashboard.domain.model.NewsMainList
import kotlinx.coroutines.flow.Flow

interface NewsDashboardRepository {
    fun getNewsDashboardMainList(query: String, pageNumber: String): Flow<Resource<NewsMainList>>

}
package com.roshanadke.dashboard.domain.use_case

import com.roshanadke.common.utils.Resource
import com.roshanadke.dashboard.domain.model.NewsMainList
import com.roshanadke.dashboard.domain.repository.NewsDashboardRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class GetNewsDashboardListUseCase(
    private val newsDashboardRepository: NewsDashboardRepository
) {

    operator fun invoke(query: String, pageNumber: String): Flow<Resource<NewsMainList>> {
        return newsDashboardRepository.getNewsDashboardMainList(query, pageNumber)
    }
}
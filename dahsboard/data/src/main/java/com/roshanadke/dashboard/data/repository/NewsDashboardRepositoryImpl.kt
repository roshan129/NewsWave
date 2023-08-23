package com.roshanadke.dashboard.data.repository

import com.roshanadke.common.utils.Resource
import com.roshanadke.dashboard.data.dto.NewsMainListDto
import com.roshanadke.dashboard.data.network.NewsApiService
import com.roshanadke.dashboard.domain.model.NewsMainList
import com.roshanadke.dashboard.domain.repository.NewsDashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class NewsDashboardRepositoryImpl(
    private val newsDashboardApiService: NewsApiService
) : NewsDashboardRepository {
    override fun getNewsDashboardMainList(
        query: String,
        pageNumber: String
    ): Flow<Resource<NewsMainList>> = flow {
        emit(Resource.Loading())
        try {
            val result = newsDashboardApiService.getNewsList(
                query = query,
                pageSize = "3",
                pageNumber = pageNumber
            )

            if (result.articles.isNotEmpty()) {
                emit(Resource.Success(result.toNewsMainList()))
            } else {
                emit(Resource.Error(message = "No data received from server"))
            }

        } catch (e: IOException) {
            emit(Resource.Error(message = "Please check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Some unexpected error occurred"))
        }

    }
}
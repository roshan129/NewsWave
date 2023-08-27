package com.roshanadke.dahsboard.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roshanadke.common.utils.DEFAULT_TECH_QUERY
import com.roshanadke.common.utils.Resource
import com.roshanadke.common.utils.STARTING_PAGE_INDEX
import com.roshanadke.dashboard.domain.model.NewsMainList
import com.roshanadke.dashboard.domain.use_case.GetNewsDashboardListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsDashboardViewModel @Inject constructor(
    private val getNewsDashboardUseCase: GetNewsDashboardListUseCase
): ViewModel() {


    private var _newsList: MutableState<NewsMainList> = mutableStateOf(NewsMainList())
    var newsList: State<NewsMainList> = _newsList

    private var _pageNumber: MutableState<Int> = mutableStateOf(1)
    val pageNumber: State<Int> = _pageNumber

    init {
        fetchInitialNewsDashboardList(
            DEFAULT_TECH_QUERY,
            STARTING_PAGE_INDEX
        )
    }

    fun incrementPageNumber() {
        _pageNumber.value++
    }

    fun setPageNumber(page: Int) {
        _pageNumber.value = page
    }

    fun getPageNumber(): Int {
        return pageNumber.value
    }

    fun fetchInitialNewsDashboardList(
        query: String,
        pageNumber: String
    ) {

        getNewsDashboardUseCase(query, pageNumber).onEach {
            when(it) {
                is Resource.Error -> {
                    Log.d("TAG", "getNewsDashboardUseCase: error")
                }

                is Resource.Loading -> {
                    Log.d("TAG", "getNewsDashboardUseCase: loading")
                }

                is Resource.Success -> {
                    it.data?.let {data ->
                        _newsList.value = _newsList.value.copy(
                            data.articles,
                            data.status,
                            data.totalResults
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadMoreNewsItems(
        query: String,
        pageNumber: String,
    ) {

        getNewsDashboardUseCase(query, pageNumber).onEach {
            when(it) {
                is Resource.Error -> {
                    Log.d("TAG", "getNewsDashboardUseCase: error")
                }

                is Resource.Loading -> {
                    Log.d("TAG", "getNewsDashboardUseCase: loading")
                }

                is Resource.Success -> {
                    it.data?.let {data ->
                        val newArticles = data.articles
                        val combinedArticles = _newsList.value.articles + newArticles

                        _newsList.value = _newsList.value.copy(
                            articles = combinedArticles,
                            status = data.status,
                            totalResults = data.totalResults
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}
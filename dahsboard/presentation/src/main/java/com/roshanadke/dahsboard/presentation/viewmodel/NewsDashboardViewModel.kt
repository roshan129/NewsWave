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
import com.roshanadke.dahsboard.presentation.NewsListDataState
import com.roshanadke.dashboard.domain.model.NewsMainList
import com.roshanadke.dashboard.domain.use_case.GetNewsDashboardListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private var _selectedChipItem: MutableState<String> = mutableStateOf(DEFAULT_TECH_QUERY)
    val selectedChipItem: State<String> = _selectedChipItem

    private var _newsListState: MutableState<NewsListDataState> = mutableStateOf(NewsListDataState())
    val newsListState: State<NewsListDataState> = _newsListState

    private var _scrollToTop: MutableState<Boolean> = mutableStateOf(false)
    val scrollToTopState: State<Boolean> = _scrollToTop

    private var _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UIEvent {
        data class showErrorMessage(val message: String): UIEvent()
    }


    init {
        fetchInitialNewsDashboardList(
            DEFAULT_TECH_QUERY,
            STARTING_PAGE_INDEX
        )
    }

    fun setSelectedChipItem(chipItem: String) {
        _selectedChipItem.value = chipItem
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

    fun updateScrollToTop(flag: Boolean) {
        _scrollToTop.value = flag
    }

    fun fetchInitialNewsDashboardList(
        query: String,
        pageNumber: String
    ) {

        getNewsDashboardUseCase(query, pageNumber).onEach {
            when(it) {
                is Resource.Error -> {
                    Log.d("TAG", "fetchInitialNewsDashboardList: insde error")
                    _newsListState.value = _newsListState.value.copy(
                        isLoading = false,
                        error = it.message ?: "Unknown error occurred"
                    )
                }

                is Resource.Loading -> {
                    _newsListState.value = _newsListState.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    updateScrollToTop(true)
                    it.data?.let {data ->
                        _newsListState.value = _newsListState.value.copy(
                            articles = data.articles,
                            isLoading = false,
                            error = null
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
                    _newsListState.value = _newsListState.value.copy(
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _newsListState.value = _newsListState.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    it.data?.let {data ->
                        val newArticles = data.articles
                        val combinedArticles = _newsListState.value.articles + newArticles

                     /*   _newsList.value = _newsList.value.copy(
                            articles = combinedArticles,
                            status = data.status,
                            totalResults = data.totalResults
                        )*/

                        _newsListState.value = _newsListState.value.copy(
                            articles = combinedArticles,
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}
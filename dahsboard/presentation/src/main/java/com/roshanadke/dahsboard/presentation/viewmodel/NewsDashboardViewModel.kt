package com.roshanadke.dahsboard.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roshanadke.common.utils.Resource
import com.roshanadke.dashboard.domain.model.NewsMainList
import com.roshanadke.dashboard.domain.use_case.GetNewsDashboardListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDashboardViewModel @Inject constructor(
    private val getNewsDashboardUseCase: GetNewsDashboardListUseCase
): ViewModel() {


    private var _newsList: MutableState<NewsMainList> = mutableStateOf(NewsMainList())
    var newsList: State<NewsMainList> = _newsList

    fun getNewsDashboardList(
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
                    Log.d("TAG", "getNewsDashboardUseCase: in success ")
                    Log.d("TAG", "getNewsDashboardUseCase: list size:" +
                            " ${it.data?.articles?.size} ")

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

}
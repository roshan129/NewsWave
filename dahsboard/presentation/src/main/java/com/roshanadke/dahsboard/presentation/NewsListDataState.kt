package com.roshanadke.dahsboard.presentation

import com.roshanadke.dashboard.domain.model.Article
import com.roshanadke.dashboard.domain.model.NewsMainList

data class NewsListDataState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false
)

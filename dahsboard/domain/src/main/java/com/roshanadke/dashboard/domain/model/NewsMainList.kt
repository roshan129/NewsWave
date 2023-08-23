package com.roshanadke.dashboard.domain.model

data class NewsMainList(
    val articles: List<Article> = mutableListOf(),
    val status: String = "",
    val totalResults: Int = 0,
)
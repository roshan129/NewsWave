package com.roshanadke.dashboard.data.dto

data class NewsMainListDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)
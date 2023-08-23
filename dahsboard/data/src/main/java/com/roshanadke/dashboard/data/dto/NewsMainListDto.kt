package com.roshanadke.dashboard.data.dto

import com.roshanadke.dashboard.domain.model.NewsMainList

data class NewsMainListDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
) {
    fun toNewsMainList(): NewsMainList {
        return NewsMainList(
            articles = articles.map { it.toArticle() },
            status = status,
            totalResults = totalResults
        )
    }
}
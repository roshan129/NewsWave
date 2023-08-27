package com.roshanadke.dashboard.data.dto

import com.roshanadke.dashboard.domain.model.Article

data class ArticleDto(
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: SourceDto? = null,
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
) {
    fun toArticle(): Article {
        return Article(
            author = author ?: "",
            content = content ?: "",
            description = description ?: "",
            publishedAt = publishedAt ?: "",
            source = source?.toSource(),
            title = title ?: "",
            url = url ?: "",
            urlToImage = urlToImage ?: ""
        )
    }
}
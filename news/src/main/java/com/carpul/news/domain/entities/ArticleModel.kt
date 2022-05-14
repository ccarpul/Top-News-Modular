package com.carpul.news.domain.entities

data class ArticleModel(

    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceModel: SourceModel,
    val title: String,
    val url: String,
    val urlToImage: String
)
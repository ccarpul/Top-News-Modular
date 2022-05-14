package com.carpul.news.domain.entities

data class ArticleListModel (
    val articles: List<ArticleModel>,
    val totalResult: String?
    )
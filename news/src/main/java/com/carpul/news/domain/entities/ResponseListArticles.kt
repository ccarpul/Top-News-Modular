package com.carpul.news.domain.entities

import com.google.gson.annotations.SerializedName

data class ResponseListArticles(
    @SerializedName("articles")
    val articleResponse: List<ArticleModel>,
    val status: String,
    val totalResults: Int
)
package com.carpul.news.repository

import com.carpul.base.network.ResultWrapper
import com.carpul.base.network.apiCallHandler
import com.carpul.news.domain.entities.ResponseListArticles
import com.carpul.news.repository.api.NewsApi

class NewsRepositoryImpl(private val newsApi: NewsApi) : NewsRepository {

    override suspend fun fetchEverythingNews(keyWord: String) : ResultWrapper<ResponseListArticles> =
        apiCallHandler { newsApi.fetchEverythingArticles(keyWord) }
}
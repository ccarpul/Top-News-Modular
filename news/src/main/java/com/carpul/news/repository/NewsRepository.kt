package com.carpul.news.repository

import com.carpul.base.network.ResultWrapper
import com.carpul.news.domain.entities.ResponseListArticles

interface NewsRepository {

    suspend fun fetchEverythingNews(keyWord: String): ResultWrapper<ResponseListArticles>
}
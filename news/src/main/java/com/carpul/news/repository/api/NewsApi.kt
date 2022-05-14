package com.carpul.news.repository.api

import com.carpul.base.BuildConfig
import com.carpul.news.domain.entities.ResponseListArticles
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("/${BuildConfig.API_VERSION}/everything?${BuildConfig.NEWS_API_KEY}")
    suspend fun fetchEverythingArticles(@Query("q") keyWord: String?) : ResponseListArticles
}
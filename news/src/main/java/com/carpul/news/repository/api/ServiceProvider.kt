package com.carpul.news.repository.api

import com.carpul.base.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Service {
    fun getApiService(): NewsApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_PROTOCOL_SECURE + BuildConfig.API_DOMAIN_DEV)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build().run {
            create(NewsApi::class.java)
        }

    private fun getOkHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }
}
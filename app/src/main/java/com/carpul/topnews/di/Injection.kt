package com.carpul.topnews.di

import com.carpul.news.repository.NewsRepository
import com.carpul.news.repository.NewsRepositoryImpl
import com.carpul.news.repository.api.Service.getApiService
import com.carpul.news.viewmodel.EverythingNewsViewModel
import com.carpul.news.viewmodel.EverythingNewsViewModelImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val everythingNewsModule = module {
    viewModel { EverythingNewsViewModelImpl(get()) as EverythingNewsViewModel }
    single { NewsRepositoryImpl(get()) as NewsRepository }
    single { getApiService() }
}
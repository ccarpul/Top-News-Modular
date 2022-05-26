package com.carpul.news.di

import com.carpul.base.viewmodel.BaseViewModel
import com.carpul.news.domain.usecases.FetchNewsEverythingUseCase
import com.carpul.news.domain.usecases.FetchNewsEverythingUseCaseImpl
import com.carpul.news.repository.NewsRepository
import com.carpul.news.repository.NewsRepositoryImpl
import com.carpul.news.repository.api.Service.getApiService
import com.carpul.news.viewmodel.EverythingNewsViewModelImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val everythingNewsModule = module {
    viewModel { EverythingNewsViewModelImpl(get()) as BaseViewModel<*>}
    single { FetchNewsEverythingUseCaseImpl(get()) as FetchNewsEverythingUseCase }
    single { NewsRepositoryImpl(get()) as NewsRepository }
    single { getApiService() }
}
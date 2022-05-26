package com.carpul.news.domain.usecases

import androidx.lifecycle.MutableLiveData
import com.carpul.base.network.ResultWrapper
import com.carpul.base.usecases.BaseUseCase
import com.carpul.news.domain.entities.ResponseListArticles
import com.carpul.news.repository.NewsRepository
import retrofit2.HttpException

class FetchNewsEverythingUseCaseImpl(private val newsRepository: NewsRepository) :
    FetchNewsEverythingUseCase,  BaseUseCase<FetchNewsEverythingUseCase.State>(){

    override fun getLiveData(): MutableLiveData<FetchNewsEverythingUseCase.State> = mutableLiveData

    override suspend fun fetch(keyword: String) =
        when(val result = newsRepository.fetchEverythingNews(keyword)) {
            is ResultWrapper.Success -> onSuccess(result.data)
            else -> onError(result)
        }

    private fun onSuccess(listArticles: ResponseListArticles){
        mutableLiveData.value = FetchNewsEverythingUseCase.State.Success(listArticles)
    }

    override fun getErrorResult(messageError: String) =
        FetchNewsEverythingUseCase.State.Error(messageError)

    override fun getFailureResult(throwable: Throwable) =
        FetchNewsEverythingUseCase.State.Failure((throwable as HttpException).message.orEmpty())
}
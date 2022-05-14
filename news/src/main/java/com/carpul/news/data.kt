package com.carpul.news

import androidx.lifecycle.MutableLiveData
import com.carpul.base.network.ResultWrapper
import com.carpul.news.domain.entities.ResponseListArticles
import com.carpul.news.domain.usecases.FetchNewsEverythingUseCase
import com.carpul.news.repository.NewsRepository
import com.carpul.news.repository.NewsRepositoryImpl
import java.lang.Exception


/*/Region Interface Use Case

fun getLiveData(): MutableLiveData<FetchNewsEverythingUseCase.State>

suspend fun fetch(keyword: String)

sealed class State {
    data class Success(val data: ResponseListArticles): State()
    data class Error(val errorMessage: String): State()
    data class Failure( val errorMessage: String): State()
}

// Region Implementation

val newsRepository: NewsRepository

override fun getLiveData(): MutableLiveData<FetchNewsEverythingUseCase.State> = mutableLiveData

override suspend fun fetch(keyword: String) =
    when(val result = newsRepository.fetchEverythingNews(keyword)) {
        is ResultWrapper.Success -> onSuccess(result.data)
        else -> onError(result)
    }

     override fun getErrorResult(messageError: String) =
        FetchNewsEverythingUseCase.State.Error(messageError)

    override fun getFailureResult(throwable: Throwable) =
        FetchNewsEverythingUseCase.State.Failure((throwable as HttpException).message.orEmpty())

        private fun onSuccess(listArticles: ResponseListArticles){
        mutableLiveData.value = FetchNewsEverythingUseCase.State.Success(listArticles)



// Region  Base use case


//T: FetchNewsEverythingUseCase.State
protected val mutableLiveData: MutableLiveData<T> = MutableLiveData()

fun onError(resultWrapper: ResultWrapper<Any>) {
    mutableLiveData.value = when (resultWrapper) {
        is ResultWrapper.NetworkError -> getFailureResult(resultWrapper.throwable)
        is ResultWrapper.GenericError -> getErrorResult("Error code: " + resultWrapper.code.toString() + ", message: " + resultWrapper.error.orEmpty())
        else -> getErrorResult("Generic Error: $resultWrapper")
    }
}

    abstract fun getErrorResult(messageError: String): T
    abstract fun getFailureResult(throwable: Throwable): T
*/
//  Compilación

//T: FetchNewsEverythingUseCase.State

/*
class DataUseCase {

    protected val mutableLiveData: MutableLiveData<State> = MutableLiveData()

    val newsRepository: NewsRepository = NewsRepositoryImpl()

    fun getLiveData(): MutableLiveData<State> = mutableLiveData

    sealed class State {
        data class Success(val data: ResponseListArticles): State()
        data class Error(val errorMessage: String): State()
        data class Failure( val errorMessage: String): State()
    }

    suspend fun fetch(keyword: String) =
        when (val result = newsRepository.fetchEverythingNews(keyword)) {
            is ResultWrapper.Success -> onSuccess(result.data)
            else -> onError(result)
        }

    fun onError(resultWrapper: ResultWrapper<Any>) {
        mutableLiveData.value = when (resultWrapper) {
            is ResultWrapper.NetworkError -> getFailureResult(resultWrapper.throwable)
            is ResultWrapper.GenericError -> getErrorResult("Error code: " + resultWrapper.code.toString() + ", message: " + resultWrapper.error.orEmpty())
            else -> getErrorResult("Generic Error: $resultWrapper")
        }
    }

    private fun onSuccess(listArticles: ResponseListArticles){
        mutableLiveData.value = State.Success(listArticles)
    }

    fun getErrorResult(messageError: String) =
        State.Error(messageError)

    fun getFailureResult(throwable: Throwable) =
        State.Failure((throwable as Exception).message.orEmpty())

}

 */
package com.carpul.news.domain.usecases

import androidx.lifecycle.MutableLiveData
import com.carpul.news.domain.entities.ResponseListArticles

interface FetchNewsEverythingUseCase {

    fun getLiveData(): MutableLiveData<State>

    sealed class State {
        data class Success(val data: ResponseListArticles): State()
        data class Error(val errorMessage: String): State()
        data class Failure( val errorMessage: String): State()
    }

    suspend fun fetch(keyword: String)
}
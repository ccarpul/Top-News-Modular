package com.carpul.news.viewmodel

import androidx.lifecycle.viewModelScope
import com.carpul.news.domain.usecases.FetchNewsEverythingUseCase
import com.carpul.news.domain.entities.ArticleListModel
import com.carpul.news.domain.entities.ResponseListArticles
import kotlinx.coroutines.launch

class EverythingNewsViewModelImpl(private val fetchNewsEverythingUseCase: FetchNewsEverythingUseCase) :
    EverythingNewsViewModel() {

    init {
        listenSource(fetchNewsEverythingUseCase.getLiveData(), ::onSuccess)
    }

    override fun fetchEverythingNews(keyword: String) {
        viewModelScope.launch {
            setState(State.Loading)
            fetchNewsEverythingUseCase.fetch(keyword)
        }
    }

    private fun onSuccess(result: FetchNewsEverythingUseCase.State) {
        when (result) {
            is FetchNewsEverythingUseCase.State.Success -> {
                setState(State.Success(mapToArticleListModel(result.data)))
            }
            is FetchNewsEverythingUseCase.State.Failure -> {
                setState(State.Error(result.errorMessage))
            }
            is FetchNewsEverythingUseCase.State.Error -> {
                setState(State.Error(result.errorMessage))
            }
        }
    }

    private fun mapToArticleListModel(data: ResponseListArticles): ArticleListModel =
        ArticleListModel(
            articles = data.articleResponse,
            totalResult = data.totalResults.toString()
        )
}
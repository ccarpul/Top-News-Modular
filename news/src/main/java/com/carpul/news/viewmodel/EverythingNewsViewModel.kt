package com.carpul.news.viewmodel

import com.carpul.base.viewmodel.BaseViewModel
import com.carpul.news.domain.entities.ArticleListModel

abstract class EverythingNewsViewModel: BaseViewModel<EverythingNewsViewModel.State>() {

    sealed class State{
        object Loading : State ()
        data class Success(val articlesListModel: ArticleListModel) : State ()
        data class Error(val errorMessage: String) : State ()
    }

    abstract fun fetchEverythingNews(keyword: String)
}
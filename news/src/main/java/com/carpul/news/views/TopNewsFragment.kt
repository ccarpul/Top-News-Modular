package com.carpul.news.views

import android.util.Log
import android.view.View
import android.widget.TextView
import com.carpul.base.view.BaseFragment
import com.carpul.news.R
import com.carpul.news.domain.entities.ArticleListModel
import com.carpul.news.viewmodel.EverythingNewsViewModel
import kotlinx.android.synthetic.main.fragment_top_news.*

class TopNewsFragment : BaseFragment<EverythingNewsViewModel, EverythingNewsViewModel.State>() {


    override fun getLayoutId(): Int = R.layout.fragment_top_news

    override fun onStateChanged(state: EverythingNewsViewModel.State) {
        Log.i(TAG, "onStateChanged: $state")
        when (state) {
            is EverythingNewsViewModel.State.Loading -> {}
            is EverythingNewsViewModel.State.Success -> processSuccess(state.articlesListModel)
            is EverythingNewsViewModel.State.Error -> processError(state.errorMessage)

        }
    }

    private fun processSuccess(articlesListModel: ArticleListModel) {

        var articles = ""
        articlesListModel.articles.forEach {
            articles += "\n" +it.title + "\n"+ it.description +"\n"+ it.publishedAt + "\n\n"
        }
        textView.text = articles
    }


    private fun processError(errorMessage: String) {
        textView.text = errorMessage
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchEverythingNews("bitcoin")
    }
}
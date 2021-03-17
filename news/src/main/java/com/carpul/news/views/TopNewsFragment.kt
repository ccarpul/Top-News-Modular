package com.carpul.news.views

import android.os.Bundle
import android.util.Log
import com.carpul.base.view.BaseActivity.Companion.TAG
import com.carpul.base.view.BaseFragment
import com.carpul.news.R
import viewmodel.TopNewsViewModel

class TopNewsFragment : BaseFragment<TopNewsViewModel, TopNewsViewModel.State>() {


    override fun getLayoutId(): Int = R.layout.fragment_top_news


    override fun onStateChanged(state: TopNewsViewModel.State) {
        TODO("Not yet implemented")
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: TopNewsFragment")
    }
}
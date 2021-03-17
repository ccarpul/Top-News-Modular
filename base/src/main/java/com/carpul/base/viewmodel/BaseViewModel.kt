package com.carpul.base.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.carpul.base.view.BaseActivity.Companion.TAG

abstract class BaseViewModel <T> (private val viewLiveData: MediatorLiveData<T> = MediatorLiveData()) : ViewModel() {

    init {
        Log.i(TAG, "Constructor: BaseViewModel:")
    }
    fun getState(): LiveData<T> = viewLiveData

    fun setState(state: T) {
        viewLiveData.value = state
    }

    fun <S> listenSource (source: LiveData<S>, onChanged: (param: S) -> Unit) {
        viewLiveData.addSource(source, onChanged)
    }

   // abstract fun getDomainViewModel(): V

    abstract fun onStateChanged(state: T)
}
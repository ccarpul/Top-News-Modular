package com.carpul.base.view

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.carpul.base.routing.FeatureRouter
import com.carpul.base.view.BaseActivity.Companion.TAG
import com.carpul.base.viewmodel.BaseViewModel

abstract class BaseFragment<V, S : Any> : Fragment(),
    BaseViewInterface<S, V> where V : BaseViewModel<S> {

    lateinit var feature: FeatureRouter  //Todo Inject through Koin

    lateinit var viewModel: V  //Todo Inject through Koin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "Constructor: BaseFragment")
        //initStateObservers()
    }

    override fun getDomainViewModel(): V = viewModel


    /*@LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getDomainViewModel(): V

    abstract fun onStateChanged(state: S)

    abstract fun initViews()*/


}

interface BaseViewInterface<S, V> where V : BaseViewModel<S> {

    @LayoutRes
    fun getLayoutId(): Int
    fun getDomainViewModel(): V
    fun initViews()
    fun onStateChanged(state: S)
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it -> it?.let { action(it) } })
}

/*fun <T, S : Any, V> T.initStateObservers() where V : BaseViewModel<S>, T : LifecycleOwner {
    observe(getDomainViewModel().getState()) { state ->

        Log.i(TAG,"onStateChanged: ${state.javaClass.simpleName}")
        onStateChanged(state)

    }
}*/


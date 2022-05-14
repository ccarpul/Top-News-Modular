package com.carpul.base.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.carpul.base.routing.FeatureRouter
import com.carpul.base.view.BaseActivity.Companion.TAG
import com.carpul.base.viewmodel.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseFragment<V, S : Any> : BaseViewInterface<S, V>, Fragment()  where V : BaseViewModel<S> {

    lateinit var feature: FeatureRouter  //Todo Inject through Koin

    private val _viewModel: BaseViewModel<S> by viewModel()//Todo Inject through Koin
    val viewModel: V
        get() = _viewModel as V

    override fun getDomainViewModel(): V = viewModel

    companion object {
        const val TAG = "TopNewsApp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStateObservers()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
}

interface BaseViewInterface<S, V> where V : BaseViewModel<S> {

    @LayoutRes
    fun getLayoutId(): Int
    fun getDomainViewModel(): V
    fun initViews() {}
    fun onStateChanged(state: S)
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it -> it?.let { action(it) } })
}

fun <T, S : Any, V> T.initStateObservers() where V : BaseViewModel<S>, T : LifecycleOwner, T: BaseViewInterface<S, V> {
    observe( getDomainViewModel().getState() ) { state ->
        Log.i(TAG,"onStateChanged: ${state.javaClass.simpleName}")
        onStateChanged(state)
    }
}


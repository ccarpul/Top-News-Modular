package viewmodel

import com.carpul.base.viewmodel.BaseViewModel

abstract class TopNewsViewModel : BaseViewModel<TopNewsViewModel.State>() {

    sealed class State{
        object Loading : State ()
    }

    abstract fun fetchNews()
}
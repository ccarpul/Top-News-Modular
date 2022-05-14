package com.carpul.base.usecases

import androidx.lifecycle.MutableLiveData
import com.carpul.base.network.ResultWrapper

abstract class BaseUseCase<T>(protected val mutableLiveData: MutableLiveData<T> = MutableLiveData()) {


    fun onError(resultWrapper: ResultWrapper<Any>) {
        mutableLiveData.value = when (resultWrapper) {
            is ResultWrapper.NetworkError -> getFailureResult(resultWrapper.throwable)
            is ResultWrapper.GenericError -> getErrorResult("Error code: " + resultWrapper.code.toString() + ", message: " + resultWrapper.error.orEmpty())
            else -> getErrorResult("Generic Error: $resultWrapper")
        }
    }

    abstract fun getErrorResult(messageError: String): T
    abstract fun getFailureResult(throwable: Throwable): T
}
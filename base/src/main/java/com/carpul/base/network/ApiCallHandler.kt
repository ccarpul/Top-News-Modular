package com.carpul.base.network

import okhttp3.Response
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> apiCallHandler(
    apiCall: suspend () -> T
): ResultWrapper<T> =

    try {
        val response = apiCall()

            if (response is Response && !response.isSuccessful)
                ResultWrapper.GenericError(response.code(), response.message())
            else ResultWrapper.Success(response)

    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultWrapper.GenericError(null, "I/O Error: ${throwable.message}")
            is HttpException -> ResultWrapper.NetworkError(throwable)
            else -> ResultWrapper.GenericError(null, "Error: ${throwable.message}")
        }
    }

sealed class ResultWrapper<out T> {

    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: String? = null) : ResultWrapper<Nothing>()
    data class NetworkError(val throwable: HttpException) : ResultWrapper<Nothing>()
}

interface IResult {
    fun onSuccess()
    fun onError()
}
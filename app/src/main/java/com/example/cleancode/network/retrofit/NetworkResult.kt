package com.example.cleancode.network.retrofit

import retrofit2.Response
import java.lang.Exception


sealed class NetworkResult <out T> {
    data class Success<out T> (val data: T): NetworkResult<T>()
    data class Error(val error: Exception, var code: Int? = null): NetworkResult<Nothing>()
    object Loading: NetworkResult<Nothing>()
}

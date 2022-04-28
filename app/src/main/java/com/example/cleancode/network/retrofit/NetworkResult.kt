package com.example.cleancode.network.retrofit



sealed class NetworkResult <T> (val data: T? = null, val message: String? = null) {
    class Success<T> (data: T) : NetworkResult<T>(data)
    class Error<T> (data: T? = null, message: String? = null) : NetworkResult<T>(data, message)
    //class Exception<T> (e: Throwable) : NetworkResult<T>(e)
}

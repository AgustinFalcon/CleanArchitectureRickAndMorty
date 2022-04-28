package com.example.cleancode.network.retrofit

import com.example.cleancode.repository.personaje.NetworkResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomRetrofitCall <T: Any> ( private val proxy: Call<T>) : Call<CustomRetrofitCall<T>> {


    override fun enqueue(callback: Callback<CustomRetrofitCall<T>>) {
        proxy.enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {
                //val networkResult = handleApi {response}
                //callback.onResponse(this@CustomRetrofitCall, Response.success(networkResult))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                //val networkResult = ApiException<T>(t)
                //callback.onFailure(this@CustomRetrofitCall, Response.success(networkResult))
            }

        })
    }


    override fun execute(): Response<CustomRetrofitCall<T>> = throw NotImplementedError()

    override fun clone(): Call<CustomRetrofitCall<T>> = CustomRetrofitCall(proxy.clone())

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()

    override fun isExecuted(): Boolean = proxy.isExecuted

    override fun isCanceled(): Boolean = proxy.isCanceled

    override fun cancel() {proxy.cancel()}

}
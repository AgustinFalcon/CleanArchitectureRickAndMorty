package com.example.cleancode.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class AppApi {

    companion object {
        private fun buildService(url: String) : ApiService {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            val builder : Retrofit.Builder = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())


            val retrofit : Retrofit = builder.build()
            val client : ApiService = retrofit.create(ApiService::class.java)

            return client
        }



        @Volatile
        private var INSTANCE: ApiService? = null

        fun getInstance(url: String) : ApiService =
            INSTANCE ?: buildService(url = url).also {
                INSTANCE = it
            }

    }
}
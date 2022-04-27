package com.example.cleancode.network.retrofit

import com.example.cleancode.network.parsedata.personaje.ResponsePersonaje
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("character")
    suspend fun getPersonajes() : Response<ResponsePersonaje>

}
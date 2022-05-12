package com.example.cleancode.network.retrofit

import com.example.cleancode.db.entities.PersonajeEntity
import com.example.cleancode.network.parsedata.personaje.RemotePersonaje
import com.example.cleancode.network.parsedata.personaje.ResponsePersonaje
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("character")
    suspend fun getPersonajes() : List<RemotePersonaje>
}
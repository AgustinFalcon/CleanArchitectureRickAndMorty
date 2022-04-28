package com.example.cleancode.repository.personaje

import com.example.cleancode.network.parsedata.personaje.RemotePersonaje
import com.example.cleancode.network.parsedata.personaje.ResponsePersonaje


interface PersonajeRepository {
    suspend fun getPersonaje() : NetworkResult
    suspend fun setUrl(url: String)
}
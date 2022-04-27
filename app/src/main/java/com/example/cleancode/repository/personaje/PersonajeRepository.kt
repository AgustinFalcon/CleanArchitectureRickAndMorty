package com.example.cleancode.repository.personaje

import com.example.cleancode.network.parsedata.personaje.RemotePersonaje


interface PersonajeRepository {

    suspend fun getPersonaje() : RemoteState
    suspend fun setUrl(url: String)

}
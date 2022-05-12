package com.example.cleancode.repository.personaje

import com.example.cleancode.network.parsedata.personaje.RemotePersonaje
import com.example.cleancode.network.parsedata.personaje.ResponsePersonaje
import com.example.cleancode.network.retrofit.NetworkResult
import kotlinx.coroutines.flow.Flow


interface PersonajeRepository {
    suspend fun getPersonaje() : Flow<NetworkResult<List<Personaje>>>
}
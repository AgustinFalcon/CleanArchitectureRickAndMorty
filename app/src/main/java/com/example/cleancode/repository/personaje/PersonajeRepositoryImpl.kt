package com.example.cleancode.repository.personaje

import android.util.Log
import com.example.cleancode.db.entities.PersonajeDAO
import com.example.cleancode.network.parsedata.personaje.RemotePersonaje
import com.example.cleancode.network.retrofit.AppApi
import javax.inject.Inject


class PersonajeRepositoryImpl @Inject constructor(private val personajeDAO: PersonajeDAO) : PersonajeRepository {

    private var baseUrl = ""

    override suspend fun getPersonaje(): RemoteState {
        return try {
            val call = AppApi.getInstance(baseUrl).getPersonajes() //dentro o fuera del try?
            Log.d(TAG, "Hello. API Call with ${call.code()} @ URL: ${call.raw().request.url}")
            if (call.isSuccessful) {
                if (!(call.body()?.personajes!!.isNullOrEmpty())) {
                    RemoteState.Success(call.body()!!.personajes)
                } else {
                    RemoteState.Loading(call.body().toString())
                }
            } else {
                RemoteState.Error(Exception(call.errorBody().toString()))
            }

        } catch (e: Exception) {
            RemoteState.Error(e)
        }
    }

    override suspend fun setUrl(url: String) {
        this.baseUrl = url
    }

    companion object {
        const val TAG = "PersonajeRepoImpl"
    }


}

sealed class RemoteState {
    data class Success(val personajes: List<RemotePersonaje>) : RemoteState()
    data class Error(val error: Exception) : RemoteState()
    data class Loading(val cargando: String) : RemoteState()
}
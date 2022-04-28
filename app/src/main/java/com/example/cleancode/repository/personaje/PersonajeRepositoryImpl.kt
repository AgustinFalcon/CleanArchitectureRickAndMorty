package com.example.cleancode.repository.personaje

import android.util.Log
import com.example.cleancode.db.entities.PersonajeDAO
import com.example.cleancode.network.parsedata.personaje.RemotePersonaje
import com.example.cleancode.network.retrofit.ApiError
import com.example.cleancode.network.retrofit.ApiException
import com.example.cleancode.network.retrofit.ApiSuccess
import com.example.cleancode.network.retrofit.AppApi
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject


class PersonajeRepositoryImpl @Inject constructor(private val personajeDAO: PersonajeDAO) : PersonajeRepository {

    private var baseUrl = ""

    override suspend fun getPersonaje(): NetworkResult {
        return try {
            val call = AppApi.getInstance(baseUrl).getPersonajes() //dentro o fuera del try?
            Log.d(TAG, "Hello. API Call with ${call.code()} @ URL: ${call.raw().request.url}")
            if (call.isSuccessful) {
                if (!(call.body()?.personajes!!.isNullOrEmpty())) {
                    //Mapear a base de datos
                    NetworkResult.Success(call.body()!!.personajes)
                } else {
                    NetworkResult.Loading(call.body().toString())
                }
            } else {
                NetworkResult.Error(Exception(call.errorBody().toString()))
            }

        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    /*suspend fun <T: Any> handleApi(execute: suspend() -> Response<T>) : NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                ApiSuccess(body)
            } else {
                ApiError(code = response.code(), message = response.message())
            }

        } catch (e: HttpException) {
            //ApiError(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            //ApiException(e = e)
        }
    }*/


    override suspend fun setUrl(url: String) {
        this.baseUrl = url
    }

    companion object {
        const val TAG = "PersonajeRepoImpl"
    }


}

sealed class NetworkResult {
    data class Success(val personajes: List<RemotePersonaje>) : NetworkResult()
    data class Error(val error: Exception) : NetworkResult()
    data class Loading(val cargando: String) : NetworkResult()
}
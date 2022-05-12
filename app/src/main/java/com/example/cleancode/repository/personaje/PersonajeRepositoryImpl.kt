package com.example.cleancode.repository.personaje

import android.util.Log
import com.example.cleancode.db.PersonajedbMapper
import com.example.cleancode.db.entities.PersonajeDAO
import com.example.cleancode.network.parsedata.personaje.PersonajeNetMapper
import com.example.cleancode.network.retrofit.ApiService
import com.example.cleancode.network.retrofit.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class PersonajeRepositoryImpl @Inject constructor(
    private val personajeDAO: PersonajeDAO,
    private val apiService: ApiService,
    private val personajeNetMapper: PersonajeNetMapper,
    private val personajedbMapper: PersonajedbMapper
) : PersonajeRepository {


    override suspend fun getPersonaje(): Flow<NetworkResult<List<Personaje>>> = flow {
        emit(NetworkResult.Loading)
        try {
            val networkPersonaje = apiService.getPersonajes()
            val personajes = personajeNetMapper.mapFromEntityList(networkPersonaje)
            for (pj in personajes) {
                personajeDAO.insert(personajedbMapper.mapToEntity(pj))
            }
            val cachePersonaje = personajeDAO.getAll()
            emit(NetworkResult.Success(personajedbMapper.mapFromEntityList(cachePersonaje)))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
        }
    }


    companion object {
        const val TAG = "PersonajeRepoImpl"
    }


}
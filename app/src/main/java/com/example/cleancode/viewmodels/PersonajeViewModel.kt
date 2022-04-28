package com.example.cleancode.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleancode.network.parsedata.personaje.RemotePersonaje
import com.example.cleancode.repository.personaje.NetworkResult
import com.example.cleancode.repository.personaje.PersonajeRepository
import com.example.cleancode.repository.personaje.PersonajeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PersonajeViewModel @Inject constructor(private val personajeRepository: PersonajeRepositoryImpl): ViewModel() {

    val personajeLiveDataSuccess = MutableLiveData<List<RemotePersonaje>>()
    var personajeError = Exception()
    var personajeLoading: String = ""

    fun setUrl(url: String) {
        CoroutineScope(Dispatchers.Default).launch {
            personajeRepository.setUrl(url = url)
        }
    }

    fun getPersonaje() {
        viewModelScope.launch {
            val responsePersonaje = personajeRepository.getPersonaje()
            when (responsePersonaje) {
                is NetworkResult.Success -> {personajeLiveDataSuccess.postValue(responsePersonaje.personajes)}
                is NetworkResult.Loading -> {personajeLoading = responsePersonaje.cargando}
                is NetworkResult.Error -> {personajeError = responsePersonaje.error}
            }
        }
    }

}
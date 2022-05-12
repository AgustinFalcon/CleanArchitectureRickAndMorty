package com.example.cleancode.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleancode.network.retrofit.NetworkResult
import com.example.cleancode.repository.personaje.Personaje
import com.example.cleancode.repository.personaje.PersonajeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonajeViewModel @Inject constructor(private val personajeRepository: PersonajeRepositoryImpl): ViewModel() {

    private val _pjDataState: MutableLiveData<NetworkResult<List<Personaje>>> = MutableLiveData()
    val pjDataState: LiveData<NetworkResult<List<Personaje>>>
      get() = _pjDataState


    fun setPjStateEvent(personajeStateEvent: PersonajeStateEvent) {
        viewModelScope.launch {
            when (personajeStateEvent) {
                is PersonajeStateEvent.GetPersonajeEvent -> {
                    personajeRepository.getPersonaje()
                        .onEach {
                            _pjDataState.value = it
                        }
                        .launchIn(viewModelScope)
                }
                is PersonajeStateEvent.None -> {
                    // TODO:
                }
            }
        }
    }


}

sealed class PersonajeStateEvent {
    object GetPersonajeEvent: PersonajeStateEvent()
    object None: PersonajeStateEvent()
}
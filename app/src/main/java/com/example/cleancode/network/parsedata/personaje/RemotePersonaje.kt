package com.example.cleancode.network.parsedata.personaje


data class RemotePersonaje (
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)
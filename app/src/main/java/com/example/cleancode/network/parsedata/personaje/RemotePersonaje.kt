package com.example.cleancode.network.parsedata.personaje


data class RemotePersonaje (
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var gender: String,
    var image: String
)
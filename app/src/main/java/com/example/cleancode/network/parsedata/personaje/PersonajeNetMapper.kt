package com.example.cleancode.network.parsedata.personaje


import com.example.cleancode.db.PersonajeEntityMapper
import com.example.cleancode.db.entities.PersonajeEntity
import com.example.cleancode.repository.personaje.Personaje
import javax.inject.Inject


class PersonajeNetMapper @Inject constructor() : PersonajeEntityMapper<RemotePersonaje, Personaje> {

    override fun mapFromEntity(entity: RemotePersonaje): Personaje {
        return Personaje(
            id = entity.id,
            name = entity.name,
            status = entity.status,
            species = entity.species,
            gender = entity.gender,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: Personaje): RemotePersonaje {
        return RemotePersonaje(
            id = domainModel.id,
            name = domainModel.name,
            status = domainModel.status,
            species = domainModel.species,
            gender = domainModel.gender,
            image = domainModel.image
        )
    }


    fun mapFromEntityList(entities: List<RemotePersonaje>): List<Personaje> {
        return entities.map { mapFromEntity(it) }
    }


}
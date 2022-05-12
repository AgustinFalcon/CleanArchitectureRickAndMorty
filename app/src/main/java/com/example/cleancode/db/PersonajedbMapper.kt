package com.example.cleancode.db

import android.app.Person
import com.example.cleancode.db.entities.PersonajeEntity
import com.example.cleancode.repository.personaje.Personaje
import javax.inject.Inject


class PersonajedbMapper @Inject constructor() : PersonajeEntityMapper<PersonajeEntity, Personaje> {

    override fun mapFromEntity(entity: PersonajeEntity): Personaje {
        return Personaje(
            id = entity.id,
            name = entity.name,
            status = entity.status,
            species = entity.species,
            gender = entity.gender,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: Personaje): PersonajeEntity {
        return PersonajeEntity(
            id = domainModel.id,
            name = domainModel.name,
            status = domainModel.status,
            species = domainModel.species,
            gender = domainModel.gender,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(entities: List<PersonajeEntity>): List<Personaje> {
        return entities.map { mapFromEntity(it) }
    }

}
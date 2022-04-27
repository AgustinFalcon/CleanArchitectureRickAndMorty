package com.example.cleancode.db.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PersonajeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(personaje: PersonajeEntity)

    @Query("SELECT * FROM table_personaje ORDER BY id ASC")
    suspend fun getAll() : List<PersonajeEntity>

    @Query("DELETE FROM table_personaje")
    suspend fun deleteAll()
}
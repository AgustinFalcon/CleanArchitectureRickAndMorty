package com.example.cleancode.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_personaje")
data class PersonajeEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "species")
    var species: String,

    @ColumnInfo(name = "gender")
    var gender: String,

    @ColumnInfo(name = "image")
    var image: String,
)
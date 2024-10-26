package com.grupo01.libreria.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "propuesta_table")
data class Propuesta (

    @PrimaryKey(autoGenerate = true)var id : Int? = null,

    var titulo : String? = null,

    var autor : String? = null,

    var genero : String? = null
)
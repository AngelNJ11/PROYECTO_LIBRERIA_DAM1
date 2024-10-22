package com.grupo01.libreria

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class Usuario (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var nombres: String? = null,
    var apellidos: String? = null,
    var nacimiento: String? = null,
    var pais: String? = null,
    var correo: String? = null,
    var contra: String? = null
)

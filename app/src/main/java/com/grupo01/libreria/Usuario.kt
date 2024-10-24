package com.grupo01.libreria

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "usuario_table")

data class Usuario (
    @PrimaryKey(autoGenerate = true)var id : Int? = null,
    var nombres : String? = null,
    var apellidos : String? = null,
    var nacimiento : String? = null,
    var pais : String? = null,
    var correo : String? = null,
    var contra : String? = null
)
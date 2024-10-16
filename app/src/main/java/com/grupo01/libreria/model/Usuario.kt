package com.grupo01.libreria.model
import java.time.LocalDate

data class Usuario (
    var id : Int? = null,
    var nombres : String? = null,
    var apellidos : String? = null,
    var nacimiento : LocalDate? = null,
    var pais : String? = null,
    var correo : String? = null,
    var contra : String? = null
)
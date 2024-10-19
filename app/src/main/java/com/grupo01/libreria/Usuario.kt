package com.grupo01.libreria

import java.time.LocalDate

data class Usuario (
    var id: Int? = null,
    var nombres: String? = null,
    var apellidos: String? = null,
    var nacimiento: LocalDate? = null,
    var pais: String? = null,
    var correo: String? = null,
    var contra: String? = null
)
{
    constructor(correo: String, contra: String) : this(
        null, null, null, null, null, correo, contra
    )
}

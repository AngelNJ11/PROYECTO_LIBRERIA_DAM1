package com.grupo01.libreria.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModelLibrodis (
    @SerialName("number") var numero : Int? = null,
    @SerialName("title") var titulo  : String? = null,
    @SerialName("releaseDate") var fechaSalida : String? = null,
    @SerialName("description") var descripcion : String? = null,
    @SerialName("pages") var paginas    : Int?    = null,
    @SerialName("cover") var portadaImg : String? = null
)
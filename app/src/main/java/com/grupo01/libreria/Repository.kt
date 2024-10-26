package com.grupo01.libreria

import com.grupo01.libreria.model.ModelLibrodis
import com.grupo01.libreria.model.Propuesta

interface Repository {
    suspend fun getLibrosDis():List<ModelLibrodis>

}
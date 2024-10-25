package com.grupo01.libreria

import com.grupo01.libreria.model.ModelLibrodis
interface Repository {
    suspend fun getLibrosDis():List<ModelLibrodis>
}
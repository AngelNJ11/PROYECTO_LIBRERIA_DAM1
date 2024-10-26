package com.grupo01.libreria

import com.grupo01.libreria.model.Propuesta

interface RepositoryPropuesta {

    suspend fun getPropuestaDis():List<Propuesta>
}
package com.grupo01.libreria

import com.grupo01.libreria.model.Propuesta


class RepositoryPropuestaImpl(private val propuestaDao: PropuestaDAO) : RepositoryPropuesta {

    override suspend fun getPropuestaDis(): List<Propuesta> {

        return propuestaDao.getPropuestaDis()
    }

}
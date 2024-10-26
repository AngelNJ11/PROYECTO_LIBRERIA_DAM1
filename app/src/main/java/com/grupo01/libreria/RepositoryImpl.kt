package com.grupo01.libreria

import android.util.Log
import com.grupo01.libreria.model.ModelLibrodis
import io.ktor.client.call.body
import io.ktor.client.request.get

class RepositoryImpl: Repository {
    override suspend fun getLibrosDis(): List<ModelLibrodis> {
        try {
            val response = Factory.client.get("https://potterapi-fedeperin.vercel.app/es/books")
            return response.body<List<ModelLibrodis>>().toList()
        }catch (e: Exception){
            Log.e("Error RepositoryImpl","El error es: ${e.message}")
            return listOf()
        }
    }

}
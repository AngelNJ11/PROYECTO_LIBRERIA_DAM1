package com.grupo01.libreria

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsuarioDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(usuario: Usuario)

    @Query("SELECT * FROM usuario_table")
    suspend fun getAllUser():List<Usuario>

    @Update
    suspend fun update(usuario: Usuario)
}
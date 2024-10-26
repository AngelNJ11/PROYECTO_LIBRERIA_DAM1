package com.grupo01.libreria

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.grupo01.libreria.model.Usuario

@Dao
interface UsuarioDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(usuario: Usuario)

    @Query("SELECT * FROM usuario_table")
    suspend fun getAllUser():List<Usuario>

    @Query("SELECT * FROM usuario_table WHERE correo = :correo LIMIT 1")
    suspend fun getUsuarioByCorreo(correo: String): Usuario?

    @Delete
    suspend fun delete(usuario: Usuario)

    @Update
    suspend fun update(usuario: Usuario)
}
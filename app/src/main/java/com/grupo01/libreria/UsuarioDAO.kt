package com.grupo01.libreria

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.grupo01.libreria.model.Usuario

@Dao
interface UsuarioDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(usuario: Usuario)



}
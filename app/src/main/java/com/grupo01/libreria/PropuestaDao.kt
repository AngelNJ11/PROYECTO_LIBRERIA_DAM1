package com.grupo01.libreria

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.grupo01.libreria.model.Propuesta

@Dao
interface PropuestaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(propuesta : Propuesta)

    @Update
    suspend fun update(propuesta : Propuesta)

    @Delete
    suspend fun delete(propuesta: Propuesta)

    @Query("SELECT * FROM propuesta_table")
    suspend fun getAllPropuesta() : List<Propuesta>

}
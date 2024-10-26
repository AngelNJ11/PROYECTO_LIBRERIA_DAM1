package com.grupo01.libreria

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.grupo01.libreria.model.Propuesta
import com.grupo01.libreria.model.Usuario


@Dao
interface PropuestaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(propuesta: Propuesta)

    @Query("SELECT * FROM propuesta_table")
    suspend fun getPropuestaDis():List<Propuesta>

    @Query("SELECT * FROM propuesta_table WHERE id = :id")
    suspend fun getPropuestaById(id: Int): Propuesta?

    @Update
    suspend fun update(propuesta: Propuesta)


}
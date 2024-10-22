package com.grupo01.libreria

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: Usuario)

    @Update
    suspend fun update(user: Usuario)

    @Delete
    suspend fun delete(user: Usuario)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUser(): List<Usuario>
}
package com.grupo01.libreria

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class UsuarioDB:RoomDatabase() {

    abstract fun usuarioDao() : UsuarioDAO
    companion object{
        @Volatile
        private  var INSTANCE : UsuarioDB? = null

        fun getDataBase(context: Context): UsuarioDB{
            return  INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsuarioDB::class.java,
                    "usuario_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
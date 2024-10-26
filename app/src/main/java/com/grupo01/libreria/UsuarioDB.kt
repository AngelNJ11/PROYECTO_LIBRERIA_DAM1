package com.grupo01.libreria

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.grupo01.libreria.model.Propuesta
import com.grupo01.libreria.model.Usuario

@Database(entities = [Usuario::class, Propuesta::class], version = 2, exportSchema = false)
abstract class UsuarioDB:RoomDatabase() {

    abstract fun usuarioDao() : UsuarioDAO
    abstract fun propuestaDao() : PropuestaDao


    companion object {
        @Volatile
        private var INSTANCE: UsuarioDB? = null

        // Paso 3: Define la migración dentro del companion object
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS propuesta_table (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        titulo TEXT NOT NULL,
                        autor TEXT NOT NULL,
                        genero TEXT NOT NULL
                    )
                """.trimIndent()
                )
            }
        }

        fun getDataBase(context: Context): UsuarioDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsuarioDB::class.java,
                    "usuario_db"
                )
                    .addMigrations(MIGRATION_1_2)  // Agrega la migración aquí
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
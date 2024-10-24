package com.grupo01.libreria

import android.content.Context
import android.content.Context.MODE_PRIVATE

class UtilsSharedPreferences {
    companion object {
        //Inicio Session
        fun createSesion(context: Context){
            val sharedPref = context.getSharedPreferences("com.midominio.miaplicacion", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean("login",true)
            editor.apply()
        }

        //Obtener Session
        fun getSesion(context: Context): Boolean{
            val sharedPref = context.getSharedPreferences("com.midominio.miaplicacion", MODE_PRIVATE)
            return sharedPref.getBoolean("login",false)
        }

        //Cerrar Session
        fun clearSesion(context: Context) {
            val sharedPref = context.getSharedPreferences("com.midominio.miaplicacion", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
        }
    }
}
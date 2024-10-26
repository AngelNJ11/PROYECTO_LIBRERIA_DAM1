package com.grupo01.libreria

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.grupo01.libreria.databinding.ActivityMainBinding
import com.grupo01.libreria.databinding.ActivityRegistrarPropuestasBinding
import com.grupo01.libreria.model.Propuesta
import com.grupo01.libreria.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrarPropuestas : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarPropuestasBinding

    private lateinit var database: UsuarioDB
    private lateinit var  propuestaDao: PropuestaDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrarPropuestasBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = UsuarioDB.getDataBase(this)
        propuestaDao =  database.propuestaDao()

        val opciones = resources.getStringArray(R.array.opciones)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinner.adapter = adapter

        binding.btnRegistrar.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val autor = binding.etAutor.text.toString()
            val genero = binding.spinner.selectedItem.toString()


            val propuesta = Propuesta(titulo = titulo, autor = autor, genero = genero)
            crearPropuesta(propuesta)

            binding.etTitulo.text.clear()
            binding.etAutor.text.clear()
            binding.spinner.clearFocus()

            startActivity(Intent(this, ListPropuesta::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }
    }
    fun crearPropuesta(propuesta :Propuesta){
        CoroutineScope(Dispatchers.IO).launch {
            propuestaDao.insert(propuesta)
        }
    }

}
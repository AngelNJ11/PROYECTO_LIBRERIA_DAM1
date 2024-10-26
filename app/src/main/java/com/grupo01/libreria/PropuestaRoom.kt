package com.grupo01.libreria

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo01.libreria.databinding.ActivityPropuestaRoomBinding
import com.grupo01.libreria.model.Propuesta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PropuestaRoom : AppCompatActivity() {

    private lateinit var database: UsuarioDB

    private lateinit var propuestaDao : PropuestaDao

    private  var propuestaData: Propuesta? = null

    private  lateinit var binding: ActivityPropuestaRoomBinding

    private lateinit var adapter : PropuestaAdapter

    private lateinit var spinnerAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPropuestaRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        database = UsuarioDB.getDataBase(this)
        propuestaDao = database.propuestaDao()

        adapter = PropuestaAdapter(emptyList(),{
            binding.edTitulo.setText(it.titulo)
            binding.edAutor.setText(it.autor)

            propuestaData = it
        }){
            deletePropuesta(it)
        }
        binding.rvPropuestas.layoutManager = LinearLayoutManager(this)

        //DEBO PONER MI LISTA DE ADAPTER A LA PROXIMA
        binding.rvPropuestas.adapter = adapter

        val opciones = resources.getStringArray(R.array.opciones)
        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.sp.adapter = spinnerAdapter

        binding.btnAgregar.setOnClickListener {

            if (propuestaData != null) {
                propuestaData.let {

                    it?.titulo = binding.edTitulo.text.toString()
                    it?.autor = binding.edAutor.text.toString()
                    it?.genero = binding.sp.selectedItem.toString()
                }
                updatePropuesta(propuestaData)
            } else {
                val propuesta = Propuesta(
                    titulo = binding.edTitulo.text.toString(),
                    autor = binding.edAutor.text.toString(),
                    genero = binding.sp.selectedItem.toString()
                )
                crearPropuesta(propuesta)
                binding.edTitulo.text.clear()
                binding.edAutor.text.clear()

            }
        }
        updateDate()
    }
    fun crearPropuesta(propuesta :Propuesta){
        CoroutineScope(Dispatchers.IO).launch {
            propuestaDao.insert(propuesta)
            updateDate()
        }
    }

    fun updatePropuesta(propuesta : Propuesta?){
        CoroutineScope(Dispatchers.IO).launch {
            propuesta?.let {
                propuestaDao.update(it)
            }
            updateDate()
            propuestaData = null
        }
    }

    fun updateDate(){
        CoroutineScope(Dispatchers.IO).launch {
            val propuestas = propuestaDao.getAllPropuesta()
            withContext(Dispatchers.Main){
                adapter.updatePropuesta(propuestas)
            }
        }
    }

    fun deletePropuesta(propuesta : Propuesta){
        CoroutineScope(Dispatchers.IO).launch {
            propuestaDao.delete(propuesta)
            updateDate()
        }
    }



}

package com.grupo01.libreria

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo01.libreria.databinding.ActivityListLibrosBinding
import com.grupo01.libreria.databinding.ActivityListPropuestaBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListPropuesta : AppCompatActivity() {

    private  lateinit var binding: ActivityListPropuestaBinding

    val database = UsuarioDB.getDataBase(this)
    val propuestaDao = database.propuestaDao()
    val service: RepositoryPropuestaImpl = RepositoryPropuestaImpl(propuestaDao)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListPropuestaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvPropuestas.layoutManager = LinearLayoutManager(this)
        binding.rvPropuestas.adapter = PropuestaAdapter(emptyList())

        CoroutineScope(Dispatchers.IO).launch {
            val lstPropuesta = service.getPropuestaDis()
            for(data in lstPropuesta){
                Log.w("Response service","data nombre: ${data.titulo} - imaen: ${data.autor} - genero : ${data.genero}" )
            }
            withContext(Dispatchers.Main){
                binding.rvPropuestas.adapter = PropuestaAdapter(lstPropuesta)
            }
        }
    }


}
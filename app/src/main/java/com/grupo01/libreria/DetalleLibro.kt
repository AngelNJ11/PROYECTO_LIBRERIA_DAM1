package com.grupo01.libreria

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.grupo01.libreria.databinding.ActivityDetalleLibroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class  DetalleLibro : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleLibroBinding

    val service: RepositoryImpl = RepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityDetalleLibroBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val numPag = intent.getIntExtra("paginas",0)
        val descripcion = intent.getStringExtra("descripcion") ?: "No hay data"
        val titulo = intent.getStringExtra("titulo")?: "No hay data"
        val fechaSalida = intent.getStringExtra("fechaSalida")?: "No hay data"
        val imgPort = intent.getStringExtra("imgPort")?: "no hay data"


        binding.txtNumPag.text = numPag.toString()
        binding.txtDescripcion.text = descripcion
        binding.txtTitulo.text = titulo
        binding.txtFechaSalida.text = fechaSalida


        CoroutineScope(Dispatchers.Main).launch {
            if (imgPort.isNotEmpty() && imgPort != "no hay data") {
                Picasso.get()
                    .load(imgPort)
                    .into(binding.imgPort)
            } else {
                binding.imgPort.setImageResource(R.mipmap.ic_launcher_round)
            }
        }
    }
}
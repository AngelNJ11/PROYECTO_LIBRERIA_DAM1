package com.grupo01.libreria

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

        val numero = intent.getIntExtra("numero", 0)

        CoroutineScope(Dispatchers.IO).launch {
            val lstLibrosd = service.getLibrosDis()

            if (numero > 0 && numero <= lstLibrosd.size) {
                val Libro = lstLibrosd[numero - 1]

                withContext(Dispatchers.Main) {
                    binding.txtTitulo.text = Libro.titulo
                    binding.txtDescripcion.text = Libro.descripcion
                    binding.txtNumPag.text = Libro.paginas.toString()
                    binding.txtFechaSalida.text = Libro.fechaSalida

                    if (!Libro.portadaImg.isNullOrEmpty()) {
                        Picasso.get()
                            .load(Libro.portadaImg)
                            .into(binding.imgPort)
                    } else {
                        binding.imgPort.setImageResource(R.mipmap.ic_launcher_round)
                    }
                }
            } else {
                Log.e("DetalleLibro", "Índice fuera de rango: $numero")
            }
        }



    }
}
package com.grupo01.libreria

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.grupo01.libreria.databinding.ActivityEditarPropuestasBinding
import com.grupo01.libreria.databinding.ActivityEditarUsuarioBinding
import com.grupo01.libreria.model.Propuesta
import com.grupo01.libreria.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditarPropuestas : AppCompatActivity() {

    private lateinit var binding: ActivityEditarPropuestasBinding
    private lateinit var propuestaDao: PropuestaDAO
    private lateinit var database: UsuarioDB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditarPropuestasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_editar_propuestas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = UsuarioDB.getDataBase(this)
        propuestaDao = database.propuestaDao()

        val sharedPref = getSharedPreferences("com.midominio.miaplicacion", MODE_PRIVATE)
        val id = sharedPref.getString("idpropuesta","No encontrado")

        fun updatePropuesta(propuesta: Propuesta?){
            CoroutineScope(Dispatchers.IO).launch {
                propuesta?.let{
                    propuestaDao.update(it)
                }
            }
        }

        lifecycleScope.launch {
            val propuesta = withContext(Dispatchers.IO) {
                id?.let {
                    propuestaDao.getPropuestaById(it.toInt())
                }
            }

            propuesta?.let {
                binding.etTitulo.setText(it.titulo)
                binding.etAutor.setText(it.autor)
                binding.spinner.clearFocus()

            } ?: run {
                binding.etTitulo.setText("Titulo no encontrado")
                binding.etAutor.setText("Autor no encontrado")
                binding.spinner.clearFocus()
            }

            binding.btnEditar.setOnClickListener {
                if (propuesta != null) {
                    propuesta.titulo = binding.etTitulo.text.toString()
                    propuesta.autor = binding.etAutor.text.toString()
                    propuesta.genero = binding.spinner.selectedItem.toString()
                    updatePropuesta(propuesta)
                    startActivity(
                        Intent(this@EditarPropuestas, MenuActivity::class.java).addFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                }
            }
        }
    }
}
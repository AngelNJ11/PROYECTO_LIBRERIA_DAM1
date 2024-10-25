package com.grupo01.libreria

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.grupo01.libreria.databinding.ActivityEditarUsuarioBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditarUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityEditarUsuarioBinding
    private val calendario: Calendar = Calendar.getInstance()
    private lateinit var usuarioDao: UsuarioDAO
    private lateinit var database: UsuarioDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = UsuarioDB.getDataBase(this)
        usuarioDao = database.usuarioDao()

        val sharedPref = getSharedPreferences("com.midominio.miaplicacion", MODE_PRIVATE)
        val correo = sharedPref.getString("correoUsuario", "No registrado")

        lifecycleScope.launch {
            val usuario = withContext(Dispatchers.IO) {
                correo?.let {
                    usuarioDao.getUsuarioByCorreo(it)
                }
            }

            usuario?.let {
                binding.etNombre.setText(it.nombres)
                binding.etApellido.setText(it.apellidos)
                binding.etCorreo.setText(it.correo)
                binding.etContra.setText(it.contra)

            } ?: run {
                binding.etNombre.setText("Usuario no encontrado")
                binding.etApellido.setText("Apellido no encontrado")
                binding.etCorreo.setText("Correo no encontrado")
                binding.etContra.setText("Contraseña no encontrada")
            }
        }

        binding.btnEditar.setOnClickListener {

        }


    }
}
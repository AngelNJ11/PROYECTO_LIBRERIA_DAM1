package com.grupo01.libreria

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.grupo01.libreria.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var usuarioDao: UsuarioDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = UsuarioDB.getDataBase(this)
        usuarioDao = db.usuarioDao()

        if (UtilsSharedPreferences.getSesion(this)) {
            startActivity(
                Intent(
                    this,
                    ListLibros::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        } else {
            binding.btnLogeo.setOnClickListener {
                val correoIngresado = binding.txtCorreo.text.toString().trim()
                val contraIngresada = binding.txtContra.text.toString().trim()

                lifecycleScope.launch {
                    val usuario = withContext(Dispatchers.IO) {
                        usuarioDao.getUsuarioByCorreo(correoIngresado)
                    }

                    if (usuario != null && usuario.contra == contraIngresada) {
                        UtilsSharedPreferences.createSesion(this@LoginActivity, usuario.correo.toString())
                        startActivity(
                            Intent(
                                this@LoginActivity,
                                EditarUsuario::class.java
                            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        )
                        mostrarToast("Ingresaste correctamente", true)
                    } else {
                        mostrarToast("Usuario o Contraseña Incorrectos", false)
                    }
                }
            }
            
            binding.btnRegistro.setOnClickListener {
                val intent = Intent(this, RegistrarUsuario::class.java)
                startActivity(intent)
            }
        }
    }

    private fun mostrarToast(mensaje: String, esExito: Boolean) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container))

        val text: TextView = layout.findViewById(R.id.textoToast)
        text.text = mensaje

        if (esExito) {
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.success_green))
        } else {
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.error_red))
        }

        with(Toast(applicationContext)) {
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}

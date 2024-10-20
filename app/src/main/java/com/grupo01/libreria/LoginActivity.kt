package com.grupo01.libreria

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.grupo01.libreria.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val usuarios = listOf(
        Usuario("jhon@gmail.com", "12345")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogeo.setOnClickListener {
            val correoIngresado = binding.txtCorreo.text.toString()
            val contraIngresada = binding.txtContra.text.toString()

            val usuarioValido = usuarios.find { it.correo == correoIngresado && it.contra == contraIngresada }

            if (usuarioValido != null) {
                mostrarToast("¡Ingresaste correctamente!", true)
            } else {
                mostrarToast("Usuario o Contraseña Incorrectos", false)
            }
        }

        binding.btnRegistro.setOnClickListener {
            val intent = Intent(this, RegistrarUsuario::class.java)
            startActivity(intent)
        }
    }

    private fun mostrarToast(mensaje: String, esExito: Boolean) {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout: View = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container))

        val text: TextView = layout.findViewById(R.id.textoToast)
        text.text = mensaje

        if (esExito) {
            layout.setBackgroundColor(getColor(R.color.success_green))
        } else {
            layout.setBackgroundColor(getColor(R.color.error_red))
        }

        with(Toast(applicationContext)) {
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}

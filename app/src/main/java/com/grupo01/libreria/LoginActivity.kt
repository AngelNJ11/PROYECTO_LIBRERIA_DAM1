package com.grupo01.libreria

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.grupo01.libreria.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (UtilsSharedPreferences.getSesion(this)) {
            startActivity(
                Intent(
                    this,
                    ListLibros::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        } else {
            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            binding.btnLogeo.setOnClickListener {
                Log.e("Input data", binding.txtCorreo.text.toString())
                Log.e("Input data", binding.txtContra.text.toString())
                if (binding.txtCorreo.text.toString()
                        .trim() == "jhon@gmail.com" && binding.txtContra.text.toString()
                        .trim() == "12345"
                ) {
                    UtilsSharedPreferences.createSesion(this)
                    startActivity(
                        Intent(
                            this,
                            ListLibros::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    )
                    mostrarToast("Ingresaste correctamente", true)
                } else {
                    mostrarToast("Usuario o Contraseña Incorrectos", false)
                }
            }
            binding.btnRegistro.setOnClickListener {
                val intent = Intent(this, RegistrarUsuario::class.java)
                startActivity(intent)
            }
        }
    }
    private fun mostrarToast(mensaje: String, esExito: Boolean) {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout: View = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container))

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

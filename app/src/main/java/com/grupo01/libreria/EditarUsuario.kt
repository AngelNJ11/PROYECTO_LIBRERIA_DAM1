package com.grupo01.libreria

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.grupo01.libreria.databinding.ActivityEditarUsuarioBinding
import com.grupo01.libreria.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditarUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityEditarUsuarioBinding
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

        fun delete(usuario: Usuario){
            CoroutineScope(Dispatchers.IO).launch {
                usuarioDao.delete(usuario)
            }
        }

        fun updateUsuario(user: Usuario?){
            CoroutineScope(Dispatchers.IO).launch {
                user?.let{
                    usuarioDao.update(it)
                }
            }
        }

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

            binding.btnEliminar.setOnClickListener {
                if (usuario != null) {
                    delete(usuario)
                }
                UtilsSharedPreferences.clearSesion(this@EditarUsuario)
                startActivity(Intent(this@EditarUsuario, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
            }

            binding.btnEditar.setOnClickListener {
                if (usuario != null) {
                    usuario.nombres = binding.etNombre.text.toString()
                    usuario.apellidos = binding.etApellido.text.toString()
                    usuario.correo = binding.etCorreo.text.toString()
                    usuario.contra = binding.etContra.text.toString()
                    updateUsuario(usuario)
                    startActivity(Intent(this@EditarUsuario, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                }
            }
        }
    }
}
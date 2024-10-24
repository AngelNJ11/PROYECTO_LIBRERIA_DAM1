package com.grupo01.libreria

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.grupo01.libreria.databinding.ActivityEditarUsuarioBinding
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

        binding.etNacimiento.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                calendario.set(Calendar.YEAR, year)
                calendario.set(Calendar.MONTH, month)
                calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd-MM-yyyy"
                val dateFormat = SimpleDateFormat(myFormat, Locale.US)
                binding.etNacimiento.setText(dateFormat.format(calendario.time))
            }, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH))

            datePickerDialog.show()
        }

        database = UsuarioDB.getDataBase(this)
        usuarioDao = database.usuarioDao()


        binding.btnEditar.setOnClickListener {

        }


    }
}
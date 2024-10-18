package com.grupo01.libreria

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.grupo01.libreria.databinding.ActivityRegistrarUsuarioBinding
import java.text.SimpleDateFormat
import java.util.Locale
import android.app.DatePickerDialog

class RegistrarUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarUsuarioBinding
    private val Calendario: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.etNacimiento.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                Calendario.set(Calendar.YEAR, year)
                Calendario.set(Calendar.MONTH, month)
                Calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd-MM-yyyy"
                val dateFormat = SimpleDateFormat(myFormat, Locale.US)
                binding.etNacimiento.setText(dateFormat.format(Calendario.time))
            }, Calendario.get(Calendar.YEAR), Calendario.get(Calendar.MONTH), Calendario.get(Calendar.DAY_OF_MONTH))

            datePickerDialog.show()
        }

        binding.btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
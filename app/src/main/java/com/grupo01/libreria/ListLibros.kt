package com.grupo01.libreria

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo01.libreria.databinding.ActivityListLibrosBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListLibros : AppCompatActivity() {
    private  lateinit var binding: ActivityListLibrosBinding

    val service: RepositoryImpl = RepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListLibrosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvLibrosdis.layoutManager = LinearLayoutManager(this)
        binding.rvLibrosdis.adapter = LibroAdapter(emptyList())

        CoroutineScope(Dispatchers.IO).launch {
            val lstLibrosd = service.getLibrosDis()
            for(data in lstLibrosd){
                Log.w("Response service","data nombre: ${data.titulo} - imaen: ${data.paginas}")
            }
            withContext(Dispatchers.Main){
                binding.rvLibrosdis.adapter = LibroAdapter(lstLibrosd)
            }
        }

        binding.btnLogOut.setOnClickListener {
            UtilsSharedPreferences.clearSesion(this)
            startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }


    }

}
package com.grupo01.libreria

import android.content.Intent
import android.graphics.ColorSpace.Model
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.grupo01.libreria.databinding.ItemLibrosdisBinding
import com.grupo01.libreria.model.ModelLibrodis
import com.squareup.picasso.Picasso

class LibroAdapter(val lstLibrodis: List<ModelLibrodis>): RecyclerView.Adapter<LibroAdapter.LibroAdapterViewHolder>(){

    class LibroAdapterViewHolder(val binding: ItemLibrosdisBinding, val lstLibrodis: List<ModelLibrodis>) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = lstLibrodis[position]
                    val context = itemView.context
                    val intent = Intent(context, DetalleLibro::class.java)
                    intent.putExtra("numero", item.numero)
                    intent.putExtra("titulo",item.titulo)
                    intent.putExtra("fechaSalida",item.fechaSalida)
                    intent.putExtra("descripcion",item.descripcion)
                    intent.putExtra("imgPort",item.portadaImg)
                    intent.putExtra("paginas",item.paginas)

                    context.startActivity(intent)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroAdapterViewHolder {
        val binding = ItemLibrosdisBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibroAdapterViewHolder(binding, lstLibrodis)
    }

    override fun getItemCount(): Int {
        return lstLibrodis.size
    }

    override fun onBindViewHolder(holder: LibroAdapterViewHolder, position: Int) {
        val rst = lstLibrodis[position]
        if(rst != null ){
            holder.binding.txtTitulo.text = rst.titulo
        }else {
            holder.binding.txtTitulo.text = "No hay data"
        }
        holder.binding.txtFechaSalida.text = rst.fechaSalida

        if (!rst.portadaImg.isNullOrEmpty()) {
            Picasso.get()
                .load(rst.portadaImg)
                .into(holder.binding.imgPort)
        } else {
            holder.binding.imgPort.setImageResource(R.mipmap.ic_launcher_round)
        }
    }



}
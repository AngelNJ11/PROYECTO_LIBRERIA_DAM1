package com.grupo01.libreria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupo01.libreria.LibroAdapter.LibroAdapterViewHolder
import com.grupo01.libreria.databinding.ItemLibrosdisBinding
import com.grupo01.libreria.databinding.ItemPropuestaBinding
import com.grupo01.libreria.model.ModelLibrodis

import com.grupo01.libreria.model.Propuesta

class PropuestaAdapter(val lstPropuesta: List<Propuesta>): RecyclerView.Adapter<PropuestaAdapter.PropuestaAdapterViewHolder>(){

    class PropuestaAdapterViewHolder(val binding: ItemPropuestaBinding, val lstPropuesta: List<Propuesta>) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropuestaAdapterViewHolder {
        val binding = ItemPropuestaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PropuestaAdapterViewHolder(binding, lstPropuesta)
    }

    override fun getItemCount(): Int {
        return  lstPropuesta.size
    }

    override fun onBindViewHolder(holder: PropuestaAdapterViewHolder, position: Int) {

        val rst = lstPropuesta[position]

        holder.binding.txtTitulo.text = rst.titulo

        holder.binding.txtAutor.text = rst.autor

        holder.binding.txtGenero.text = rst.genero
    }
}
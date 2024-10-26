package com.grupo01.libreria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupo01.libreria.databinding.ItemPropuestaBinding
import com.grupo01.libreria.model.Propuesta

class PropuestaAdapter(var lstPropuesta: List<Propuesta>, private val actionUpdate: (propuesta: Propuesta) -> Unit, private val actionDelete: (propuesta: Propuesta) -> Unit): RecyclerView.Adapter<PropuestaAdapter.PropuestaAdapterViewHolder>() {

    class PropuestaAdapterViewHolder(val binding: ItemPropuestaBinding) : RecyclerView.ViewHolder(binding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropuestaAdapterViewHolder {
        val binding =
            ItemPropuestaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PropuestaAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lstPropuesta.size
    }

    override fun onBindViewHolder(holder: PropuestaAdapterViewHolder, position: Int) {

        val propuesta = lstPropuesta[position]

        holder.binding.txtTitulo.text = propuesta.titulo

        holder.binding.txtAutor.text = propuesta.autor

        holder.binding.txtGenero.text = propuesta.genero

        holder.binding.btnEliminarPropuesta.setOnClickListener {
            actionDelete(propuesta)
        }
        holder.binding.btnEditarPropuesta.setOnClickListener {
            actionUpdate(propuesta)
        }
    }

    fun updatePropuesta(newList: List<Propuesta>) {
        lstPropuesta = newList
        notifyDataSetChanged()
    }
}




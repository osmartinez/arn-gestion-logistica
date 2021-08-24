package com.arneplant.logisticainterna_kot2.adapter

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.arneplant.logisticainterna_kot2.R
import com.arneplant.logisticainterna_kot2.delegate.RestaurarConsumosDelegate
import com.arneplant.logisticainterna_kot2.model.Labor
import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.dto.Consumo
import kotlinx.android.synthetic.main.dialog_detalles_consumos.*
import kotlinx.android.synthetic.main.entry_consumicion_maquina.view.*
import kotlinx.android.synthetic.main.entry_labor_disponible.view.*

class LaboresDisponiblesAdapter : RecyclerView.Adapter<LaboresDisponiblesAdapter.ViewHolder>() {
    var superheros: MutableList<Labor>  = ArrayList()
    lateinit var context:Context
    fun LaboresDisponiblesAdapter(superheros : MutableList<Labor>, context: Context){
        this.superheros = superheros
        this.context = context
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = superheros.get(position)
        holder.bind(item, context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.entry_labor_disponible, parent, false))
    }
    override fun getItemCount(): Int {
        return superheros.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val superheroName = view.findViewById(R.id.boton) as info.hoang8f.widget.FButton

        fun bind(superhero:Labor, context: Context){
            superheroName.text = superhero.descripcion

            itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context, superhero.descripcion, Toast.LENGTH_SHORT).show() })
        }

    }
}
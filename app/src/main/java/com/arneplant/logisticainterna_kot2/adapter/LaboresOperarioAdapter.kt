package com.arneplant.logisticainterna_kot2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.arneplant.logisticainterna_kot2.R
import com.arneplant.logisticainterna_kot2.delegate.EliminarLaborOperarioDelegate
import com.arneplant.logisticainterna_kot2.model.Labor
import com.arneplant.logisticainterna_kot2.model.LaborOperario

class LaboresOperarioAdapter : RecyclerView.Adapter<LaboresOperarioAdapter.ViewHolder>() {
    var superheros: MutableList<LaborOperario>  = ArrayList()
    lateinit var context: Context
    var eliminarDelegate: EliminarLaborOperarioDelegate? = null
    fun LaboresOperarioAdapter(superheros : MutableList<LaborOperario>, context: Context, eliminarDelegate: EliminarLaborOperarioDelegate){
        this.superheros = superheros
        this.context = context
        this.eliminarDelegate = eliminarDelegate
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = superheros.get(position)
        holder.bind(item, context,eliminarDelegate)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.entry_labor_operario, parent, false))
    }
    override fun getItemCount(): Int {
        return superheros.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val superheroName = view.findViewById(R.id.boton) as info.hoang8f.widget.FButton
        val descripcionView = view.findViewById<TextView>(R.id.tbDescripcion)
        val btnEliminar = view.findViewById<info.hoang8f.widget.FButton>(R.id.btEliminar)
        val horaView = view.findViewById<TextView>(R.id.tbFechaInicioLabor)

        fun bind(laborOperario: LaborOperario, context: Context,eliminarDelegate: EliminarLaborOperarioDelegate?){
            descripcionView.setText(laborOperario.descripcion)
            horaView.setText("a las "+laborOperario.fechaInicioLabor.hours+":"+laborOperario.fechaInicioLabor.minutes)
           if(eliminarDelegate!=null){
               btnEliminar.setOnClickListener {
                   eliminarDelegate.eliminar(laborOperario)
               }
           }
        }

    }
}
package com.arneplant.logisticainterna_kot2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.arneplant.logisticainterna_kot2.R
import com.arneplant.logisticainterna_kot2.model.dto.AgrupacionCola
import com.arneplant.logisticainterna_kot2.model.dto.AgrupacionUbicacionTarea
import com.arneplant.logisticainterna_kot2.model.dto.UbicacionTarea
import kotlinx.android.synthetic.main.entry_ubicacion_tarea.view.*

class TareaUbicacionAdapter (private val context: Context,
                                   private val dataSource:List<AgrupacionUbicacionTarea>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var views: ArrayList<View> = ArrayList(dataSource.size)

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.entry_ubicacion_tarea, parent, false)
        this.views.add(position,rowView)
        var item = getItem(position) as AgrupacionUbicacionTarea
        var talla = ""
        if(item.esMaquina()){
            talla = item.talla
            talla = "<"+talla+">"
        }
        rowView.tbNombreUbicacion.text = "${talla} ${item.ubicacion.replace("ESPUMA","")}"
        rowView.tbNumeroCajas.text = if(item.numCajas==1) "1 caja" else "${item.numCajas} cajas"
        rowView.tbPares.text = "${item.pares.toInt()} pares"
        //rowView.tbParesPendientesTarea.text = "faltan ${item.cantidadPendiente.toInt()}"

        if(item.esMaquina()){
            if(item.esEjecucion()){
                rowView.panel1.setBackgroundColor(ContextCompat.getColor(context, R.color.violet))
                rowView.panel2.setBackgroundColor(ContextCompat.getColor(context, R.color.violet))
            }
            else{
                rowView.panel1.setBackgroundColor(ContextCompat.getColor(context, R.color.orange))
                rowView.panel2.setBackgroundColor(ContextCompat.getColor(context, R.color.orange))
            }
        }
        else{
            rowView.panel1.setBackgroundColor(ContextCompat.getColor(context, R.color.lightBlue))
            rowView.panel2.setBackgroundColor(ContextCompat.getColor(context, R.color.lightBlue))
        }

        return rowView
    }

}
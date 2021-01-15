package com.arneplant.logisticainterna_kot2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.arneplant.logisticainterna_kot2.R
import com.arneplant.logisticainterna_kot2.model.TareaPendiente
import com.arneplant.logisticainterna_kot2.model.UbicacionPaquetes
import kotlinx.android.synthetic.main.entry_tarea_maquina.view.*
import kotlinx.android.synthetic.main.entry_ubicacion_paquetes.view.*

class TareaProgramadaAdapter (private val context: Context,
                        private val dataSource:List<TareaPendiente>): BaseAdapter() {
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
        val rowView = inflater.inflate(R.layout.entry_tarea_maquina, parent, false)
        this.views.add(position,rowView)
        var item = getItem(position) as TareaPendiente
        rowView.tbTitulo.text = item.toString()
        rowView.tbCliente.text = item.nombreCliente
        //rowView.tbParesPendientes.text = "${item.cantidadPendiente} pares pendientes"
        if(item.isNuevo){
            rowView.tbTitulo.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
            rowView.panel1.setBackgroundColor(ContextCompat.getColor(context, R.color.orange))
            rowView.panel2.setBackgroundColor(ContextCompat.getColor(context, R.color.orange))
        }
        else{
            rowView.tbTitulo.setBackgroundColor(ContextCompat.getColor(context, R.color.purple))
            rowView.panel1.setBackgroundColor(ContextCompat.getColor(context, R.color.lightBlue))
            rowView.panel2.setBackgroundColor(ContextCompat.getColor(context, R.color.lightBlue))
        }
        return rowView
    }

    private fun lanzarPopup() {

    }
}
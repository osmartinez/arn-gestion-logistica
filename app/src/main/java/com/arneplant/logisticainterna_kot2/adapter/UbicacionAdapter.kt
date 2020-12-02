package com.arneplant.logisticainterna_kot2.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.BaseAdapter
import android.widget.ListView
import com.arneplant.logisticainterna_kot2.R
import com.arneplant.logisticainterna_kot2.model.UbicacionPaquetes
import kotlinx.android.synthetic.main.entry_ubicacion_paquetes.view.*

class UbicacionAdapter (private val context: Context,
                        private val dataSource:List<UbicacionPaquetes>): BaseAdapter() {
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
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.entry_ubicacion_paquetes, parent, false)
        this.views.add(position,rowView)
        var item = getItem(position) as UbicacionPaquetes
        rowView.tbNombreUbicacion.text = item.nombre
        rowView.tbPaquetesDescargados.text = item.toString()
        return rowView
    }

    private fun lanzarPopup() {

    }
}
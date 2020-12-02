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
import androidx.core.content.ContextCompat
import com.arneplant.logisticainterna_kot2.R
import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.dto.Consumo
import kotlinx.android.synthetic.main.entry_consumo.view.*


class ConsumoAdapter (private val context: Context,
                             private val dataSource:List<Consumo>): BaseAdapter() {
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
        val rowView = inflater.inflate(R.layout.entry_consumo, parent, false)
        this.views.add(position,rowView)
        var item = getItem(position) as Consumo
        rowView.tbCodigoOrden.text = "${item.codigoOrden} - ${item.talla} - ${item.cliente}"
        rowView.tbParesPendientes.text = "${item.cantidadPendiente} pares pendientes"

        if(item.cantidadPendiente>0){
            rowView.panel1.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.red
                ))
        }
        else{
            rowView.panel1.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.lightGreen
                ))
        }
        return rowView
    }

    private fun lanzarPopup() {

    }
}
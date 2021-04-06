package com.arneplant.logisticainterna_kot2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.arneplant.logisticainterna_kot2.R
import com.arneplant.logisticainterna_kot2.model.dto.Consumo
import com.arneplant.logisticainterna_kot2.model.dto.UtillajeUbicacion
import kotlinx.android.synthetic.main.entry_consumo.view.*
import kotlinx.android.synthetic.main.entry_utillaje_ubicacion.view.*

class UtillajeUbicacionAdapter (private val context: Context,
                      private val dataSource:List<UtillajeUbicacion>): BaseAdapter() {
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
        val rowView = inflater.inflate(R.layout.entry_utillaje_ubicacion, parent, false)
        this.views.add(position,rowView)
        var item = getItem(position) as UtillajeUbicacion
        rowView.talla.text = item.tallaUtillaje
        rowView.ubicacion.text = item.ubicacion
        rowView.codUtillaje.text = item.codUtillaje


        return rowView
    }

}
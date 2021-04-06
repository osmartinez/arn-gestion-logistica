package com.arneplant.logisticainterna_kot2.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.core.content.ContextCompat
import com.arneplant.logisticainterna_kot2.R
import com.arneplant.logisticainterna_kot2.model.UbicacionPaquetes
import kotlinx.android.synthetic.main.entry_ubicacion_paquetes.view.*

class UbicacionAdapter (val context: Context,
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
        return 1
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.entry_ubicacion_paquetes, parent, false)
        this.views.add(position,rowView)
        var item = getItem(position) as UbicacionPaquetes
        rowView.tbUbicacion.text = item.nombreUbicacion
        rowView.tbBarquillasUbicadas.text = "${item.barquillas.size} barquillas"
        rowView.tbPaquetesUbicados.text = "${item.paquetes.size} paquetes"
        rowView.tbUtillajesUbicados.text = "${item.utillajes.size} utillajes"

        if (item.isSeleccionada) {
            rowView.tbUbicacion.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_midnight_blue
                )
            )
            rowView.panelUbicacion1.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_emerald
                )
            )
            rowView.panelUbicacion2.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_emerald
                )
            )
            rowView.panelUbicacion3.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_emerald
                )
            )
        } else {
            rowView.tbUbicacion.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_midnight_blue
                )
            )
            rowView.panelUbicacion1.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_carrot
                )
            )
            rowView.panelUbicacion3.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_carrot
                )
            )
            rowView.panelUbicacion2.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_carrot
                )
            )
        }

        return rowView
    }

    private fun lanzarPopup() {

    }
}
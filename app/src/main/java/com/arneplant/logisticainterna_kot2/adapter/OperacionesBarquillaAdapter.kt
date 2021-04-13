package com.arneplant.logisticainterna_kot2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.arneplant.logisticainterna_kot2.R
import com.arneplant.logisticainterna_kot2.delegate.FiltroOperacionCambiadoDelegate
import com.arneplant.logisticainterna_kot2.model.OrdenFabricacionOperacion
import kotlinx.android.synthetic.main.entry_filtro_operacion.view.*

class OperacionesBarquillaAdapter (private val context: Context,
                      private val dataSource:List<OrdenFabricacionOperacion>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var filtroCambiado: FiltroOperacionCambiadoDelegate? = null
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

    fun setFiltroCambiadoListener(listener: FiltroOperacionCambiadoDelegate){
        this.filtroCambiado = listener
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.entry_filtro_operacion, parent, false)
        this.views.add(position,rowView)
        var item = getItem(position) as OrdenFabricacionOperacion
        var descripcion = item.descripcion
        if(descripcion.length>10){
            descripcion = descripcion.substring(0,10)
        }
        rowView.filtro_op.text = "${descripcion}"

        rowView.filtro_op.setOnClickListener {
            for(ofo in dataSource){
                ofo.isSeleccionado=false
            }
            item.isSeleccionado = !item.isSeleccionado
            notifyDataSetChanged()

            if(filtroCambiado!=null){
                filtroCambiado?.seccionSeleccionada(item.codSeccion)
            }
        }

        if(item.isSeleccionado){
            rowView.filtro_op.setBackgroundColor(
                ContextCompat.getColor(
                   context,
                    R.color.fbutton_color_emerald
                ))
        }
        else{
            rowView.filtro_op.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_carrot
                ))
        }
//        if(item.cantidadPendiente>0){
//            rowView.panel1.setBackgroundColor(
//                ContextCompat.getColor(
//                    context,
//                    R.color.red
//                ))
//        }
//        else{
//            rowView.panel1.setBackgroundColor(
//                ContextCompat.getColor(
//                    context,
//                    R.color.lightGreen
//                ))
//        }
        return rowView
    }

}
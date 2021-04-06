package com.arneplant.logisticainterna_kot2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.arneplant.logisticainterna_kot2.R
import com.arneplant.logisticainterna_kot2.delegate.FiltroUtillajeCambiadoDelegate
import com.arneplant.logisticainterna_kot2.model.dto.FiltroUtillaje
import com.arneplant.logisticainterna_kot2.model.dto.UtillajeUbicacion
import kotlinx.android.synthetic.main.entry_filtro_utillaje.view.*
import kotlinx.android.synthetic.main.entry_utillaje_ubicacion.view.*

class UtillajeFiltroAdapter (private val context: Context,
                                private val dataSource:List<FiltroUtillaje>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private var views: ArrayList<View> = ArrayList(dataSource.size)

    private var filtroUtillajeCambiado: FiltroUtillajeCambiadoDelegate? = null

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

    fun setFiltroCambiadoDelegate(delegate: FiltroUtillajeCambiadoDelegate){
        this.filtroUtillajeCambiado = delegate
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.entry_filtro_utillaje, parent, false)
        this.views.add(position,rowView)
        var item = getItem(position) as FiltroUtillaje
        rowView.prefijo.text = item.prefijo
        rowView.prefijo.setOnClickListener {
            item.isActivo = !item.isActivo

            if(this.filtroUtillajeCambiado!=null){
                this.filtroUtillajeCambiado?.filtroCambiado()
            }

            notifyDataSetChanged()
        }

        if(item.isActivo){
            rowView.prefijo.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_carrot
                )
            )
        }
        else{
            rowView.prefijo.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_green_sea
                )
            )
        }

        return rowView
    }

}
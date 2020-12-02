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
import com.arneplant.logisticainterna_kot2.delegate.BuscadorFragmentDelegate
import com.arneplant.logisticainterna_kot2.delegate.RestaurarConsumosDelegate
import com.arneplant.logisticainterna_kot2.model.CodigoRespuesta
import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.Respuesta
import com.arneplant.logisticainterna_kot2.model.dto.Consumo
import com.arneplant.logisticainterna_kot2.network_implementation.GestionPaqueteMaquina
import com.arneplant.logisticainterna_kot2.util.Utils
import kotlinx.android.synthetic.main.dialog_detalles_consumos.*
import kotlinx.android.synthetic.main.entry_consumicion_maquina.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ConsumoMaquinaAdapter(
    private val context: Context,
    private val dataSource: ArrayList<Maquina>
) : BaseAdapter() {
    private var delegate: RestaurarConsumosDelegate? =null

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

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
        val rowView = inflater.inflate(R.layout.entry_consumicion_maquina, parent, false)
        this.views.add(position, rowView)
        var item = getItem(position) as Maquina

        if(context is RestaurarConsumosDelegate){
            delegate = context
        }
        var numCajas = 0
        var ag = item.consumos.groupBy { x->x.codPrepaqueteAgrupacion }

        for(grupo in ag){
            if(grupo.key != null){
                numCajas++
            }
            else{
                numCajas+=grupo.value.size
            }
        }

        rowView.tbParesRecogidos.text =
            "${numCajas} cajas  |  ${item.consumos.sumByDouble { x -> x.cantidadPaquete }} pares"
        rowView.tbNombreMaquina.text = item.nombre
        var idOrdenes = ArrayList<Int>()
        for (consumo in item.consumos) {
            if (!idOrdenes.contains(consumo.idOrden)) {
                idOrdenes.add(consumo.idOrden)
            }
        }
        rowView.tbNumeroOrdenes.text = "${idOrdenes.size} ordenes de fabricación"

        if (item.isSeleccionada) {
            rowView.tbNombreMaquina.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_midnight_blue
                )
            )
            rowView.panel1.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_emerald
                )
            )
            rowView.panel2.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_emerald
                )
            )
        } else {
            rowView.tbNombreMaquina.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_midnight_blue
                )
            )
            rowView.panel1.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_carrot
                )
            )
            rowView.panel2.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.fbutton_color_carrot
                )
            )
        }

        rowView.btRestaurar.setOnClickListener {
            delegate?.desconsumir(item)
        }


        rowView.btMasInfo.setOnClickListener {
            var dialog: Dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            if (dialog.window != null) {
                var colorDrawable: ColorDrawable = ColorDrawable(Color.TRANSPARENT)
                dialog.window.setBackgroundDrawable(colorDrawable)
            }
            dialog.setContentView(R.layout.dialog_detalles_consumos)
            dialog.setCancelable(false)

            val consumos = ArrayList<Consumo>()

            data class Key(val codigoOrden: String, val tallaUtillaje: String)

            fun Consumo.toKey() = Key(codigoOrden, tallaUtillaje)

            for (grupo in item.consumos.groupBy { it.toKey() }) {
                var nuevoConsumo = Consumo()
                nuevoConsumo.idOrden = grupo.value.first().idOrden
                nuevoConsumo.codigoOrden = grupo.key.codigoOrden
                nuevoConsumo.cantidadPendiente = grupo.value.last().cantidadPendiente
                nuevoConsumo.cantidadFabricada = grupo.value.last().cantidadFabricada
                nuevoConsumo.cantidadFabricar = grupo.value.last().cantidadFabricar
                nuevoConsumo.cliente = grupo.value.last().cliente
                nuevoConsumo.talla = grupo.key.tallaUtillaje
                consumos.add(nuevoConsumo)
            }

            consumos.sortBy { it.codigoOrden }

            dialog.lista.adapter = ConsumoAdapter(context, consumos)
            dialog.show()
            dialog.btAceptar.setOnClickListener { dialog.dismiss() }
        }


        return rowView
    }

    private fun lanzarPopup() {

    }
}
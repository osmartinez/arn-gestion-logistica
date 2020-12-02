package com.arneplant.logisticainterna_kot2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arneplant.logisticainterna_kot2.delegate.BuscadorFragmentDelegate
import com.arneplant.logisticainterna_kot2.fragment.LogFragment
import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.dto.PrepaqueteInfo
import com.arneplant.logisticainterna_kot2.network_implementation.MaquinaService
import com.arneplant.logisticainterna_kot2.network_implementation.PrepaqueteService
import com.arneplant.logisticainterna_kot2.util.Tipo
import com.arneplant.logisticainterna_kot2.util.Utils
import kotlinx.android.synthetic.main.activity_modificar_prepaquete.*
import kotlinx.android.synthetic.main.activity_programacion_maquina.*
import kotlinx.android.synthetic.main.activity_programacion_maquina.frgLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ModificarPrepaqueteActivity : AppCompatActivity(), BuscadorFragmentDelegate {

    private var log: LogFragment? = null
    private var codigoEtiqueta : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_prepaquete)
        this.title = "Editar etiqueta"
        this.log = frgLog as LogFragment

    }

    fun atras(v: View){
        this.finish()
    }

    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        when (Utils.getTipo(msg)) {
            Tipo.PrePaquete -> {
                cargarPrepaquete(msg)

            }
            Tipo.PrePaqueteAgrupacion -> {
                cargarPrepaquete(msg)

            }
            else -> {
            }
        }
    }

    private fun cargarPrepaquete(msg: String) {
        val service = PrepaqueteService()
        val call = service.buscarPorCodigo(msg)
        call.enqueue(object: Callback<List<PrepaqueteInfo>> {
            override fun onFailure(call: Call<List<PrepaqueteInfo>>, t: Throwable) {
                log!!.log(t.message ?: "Error en la petición", false)
                codigoEtiqueta = ""
            }

            override fun onResponse(call: Call<List<PrepaqueteInfo>>, response: Response<List<PrepaqueteInfo>>) {
                if(response.body()!=null){
                    codigoEtiqueta = msg
                    cargarInfo(response.body()!!)
                }
                else{
                    codigoEtiqueta = ""
                }
            }

        })
    }

    private fun cargarInfo(prepaquetes: List<PrepaqueteInfo>){
        tbOF.setText("")
        tbTalla.setText("")
        tbCantidad.setText("")
        var cantidad: Int = 0
        for(pre in prepaquetes){
            tbOF.setText(tbOF.text.toString() + ","+pre.codigo)
            tbTalla.setText(pre.talla)
            cantidad+= pre.cantidad.toInt()
        }
        tbCantidad.setText(cantidad.toString())
    }

    fun guardarCambios(v: View){
        var prepaquete: PrepaqueteInfo = PrepaqueteInfo()
        try{
            prepaquete.cantidad = tbCantidad.text.toString().toDouble()
            prepaquete.codigoEtiqueta = codigoEtiqueta
        }catch (err:Exception){
            log!!.log(err.message ?: "Error lectura campos", false)
            return
        }
        val service = PrepaqueteService()
        val call = service.actualizarCantidad(prepaquete)
        call.enqueue(object: Callback<List<PrepaqueteInfo>> {
            override fun onFailure(call: Call<List<PrepaqueteInfo>>, t: Throwable) {
                log!!.log(t.message ?: "Error en la petición", false)
            }

            override fun onResponse(call: Call<List<PrepaqueteInfo>>, response: Response<List<PrepaqueteInfo>>) {
                if(response.body()!=null){
                    cargarInfo(response.body()!!)
                    log!!.log("Cantidad actualizada", true)
                }
            }

        })
    }

}

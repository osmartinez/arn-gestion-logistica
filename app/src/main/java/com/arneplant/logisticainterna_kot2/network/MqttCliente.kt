package com.arneplant.logisticainterna_kot2.network

import android.content.Context
import android.util.Log
import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.MaquinaColaTrabajo
import com.arneplant.logisticainterna_kot2.model.dto.ColaMaquinaActualizada
import com.arneplant.logisticainterna_kot2.model.dto.Consumo
import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import com.google.gson.Gson
import org.json.JSONArray

object MqttCliente {

    private var cliente : MqttClientHelper? = null

    fun iniciar(ctx: Context,operario: String){
        this.cliente = MqttClientHelper(ctx,operario)
    }

    fun colaMaquinaActualizada(maquina: Maquina, cola :List<MaquinaColaTrabajo>, colaBorrados:List<MaquinaColaTrabajo>){
        var colaMaquina = ColaMaquinaActualizada(cola,colaBorrados,maquina.id,if (maquina.idBancada==null) 0 else maquina.idBancada)
        val topic = "/bancada/${colaMaquina.idBancada}/maquina/${maquina.id}/programacion"
        val msg = Gson().toJson(colaMaquina)
        publicar(msg,topic)
    }

    fun asociarTarea(ipAutomata: String,numPrensa: Int, idTarea:Int, paresTarea: Int, codigoOF: String, utillaje:String,tallaUtillaje:String, tallaArticulo:String, codigoEtiqueta:String, idOrden: Int, idOperacion: Int, nombreCliente: String, codigoArticulo:String, paresUtillaje: Int, idOperario: Int){
        val topic = "/moldeado/plc/${ipAutomata.padStart(LEN_IPAUTOMATA)}/asociarTarea"

        val msg = "${numPrensa.toString().padStart(LEN_NUMPRENSA)};" +
                "${idTarea.toString().padStart(LEN_IDTAREA,'0')};" +
                "${paresTarea.toString().padStart(LEN_PARESTAREA,'0')};" +
                "${codigoOF.padStart(LEN_CODIGOOF)};" +
                "${utillaje.padStart(LEN_UTILLAJE)};" +
                "${tallaUtillaje.padStart(LEN_TALLAUTILLAJE)};" +
                "${tallaArticulo.padStart(LEN_TALLAARTICULO)};" +
                "${codigoEtiqueta.padStart(LEN_ETIQUETA)};" +
                "${idOrden.toString().padStart(LEN_IDORDEN,'0')};" +
                "${idOperacion.toString().padStart(LEN_IDOPERACION,'0')};" +
                "${nombreCliente.padStart(LEN_NOMBRECLIENTE)};" +
                "${codigoArticulo.padStart(LEN_CODIGOARTICULO)};" +
                "${paresUtillaje.toString().padStart(LEN_PARESUTILLAJE)};"+
                "${idOperario.toString().padStart(LEN_IDOPERARIO)};"

        publicar(msg,topic)
    }

    fun consumirTarea(consumo: Consumo){
        val topic = "/ordenFabricacion/${consumo.idOrden}/${consumo.codSeccion}/consumo"
        val msg = """
            {
            "CodigoOrden": ${consumo.codigoOrden},
            "IdMaquina": ${consumo.idMaquina},
            "CodSeccion": ${consumo.codSeccion},
            "CantidadPaquete": ${consumo.cantidadPaquete}
            }
        """.trimIndent()
        publicar(msg,topic)
    }

    private fun publicar(msg: String, topic:String){
        try {
            this.cliente?.publish(topic,msg)
        }catch (ex: Exception){
            Log.d("error mqtt",ex.message)
        }
    }

    fun cerrar(){
        try {
            this.cliente?.destroy()
        }catch (ex: Exception){
            Log.d("error mqtt ",ex.message)
        }
    }

}
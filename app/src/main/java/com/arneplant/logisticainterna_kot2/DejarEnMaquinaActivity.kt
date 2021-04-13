package com.arneplant.logisticainterna_kot2

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arneplant.logisticainterna_kot2.adapter.TareaProgramadaAdapter
import com.arneplant.logisticainterna_kot2.application.Store
import com.arneplant.logisticainterna_kot2.delegate.BuscadorFragmentDelegate
import com.arneplant.logisticainterna_kot2.fragment.LogFragment
import com.arneplant.logisticainterna_kot2.model.*
import com.arneplant.logisticainterna_kot2.model.dto.*
import com.arneplant.logisticainterna_kot2.network.MqttCliente
import com.arneplant.logisticainterna_kot2.network_implementation.*
import com.arneplant.logisticainterna_kot2.util.Dialogos
import com.arneplant.logisticainterna_kot2.util.Tipo
import com.arneplant.logisticainterna_kot2.util.Utils
import kotlinx.android.synthetic.main.activity_dejar_en_maquina.frgLog
import kotlinx.android.synthetic.main.activity_dejar_en_maquina.lista
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


class DejarEnMaquinaActivity : AppCompatActivity(), BuscadorFragmentDelegate {
    var adapter: TareaProgramadaAdapter? = null
    var tareas: ArrayList<AgrupacionCola> = ArrayList()
    private var log: LogFragment? = null
    private var ctx: Context? = null
    private var buzzer: MediaPlayer? = null
    private var buzzerMultioperacion: MediaPlayer? = null
    private var idOperario: Int = 0
    private var codigoMaquina = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dejar_en_maquina)

        this.title = "${Store.CODIGO_OPERARIO} - Dejar en máquina"

        this.adapter = TareaProgramadaAdapter(this, this.tareas)
        this.lista.adapter = this.adapter
        this.log = frgLog as LogFragment
        this.ctx = this

        this.buzzer = MediaPlayer.create(this, R.raw.buzzer)
        this.buzzerMultioperacion = MediaPlayer.create(this, R.raw.multioperacion)

        this.idOperario = Store.ID_OPERARIO
    }

    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        when (Utils.getTipo(msg)) {
            Tipo.Maquina -> {
                this.codigoMaquina = msg
            }

            Tipo.Barquilla->{
                this.programarBarquilla(msg)
            }
            else -> {
            }
        }
    }

    private fun asignarAMaquina(infos: List<PrepaqueteSeccionDTO>, maquina: Maquina){
        val idsOrden = infos.map { it.idOrden }
        val idsOrdenDistinto = infos.map{it.idOrden}.distinct()

        if(idsOrden.size != idsOrdenDistinto.size){
            Dialogos.mostrarDialogoMultiOperacionAsociar(infos,maquina,::asignarAMaquinaMulti,ctx!!)
        }
        else{
            val idsTareas = infos.map{it.idTarea}.distinct()
            asociarPrepaquete(infos.first(), maquina)
            for(id in idsTareas){
                asociarTareaEjecucion(
                    id,
                    if (infos.first().agrupacion==null) 0 else infos.first().agrupacion,
                    maquina
                )
            }

        }
    }

    private fun asignarAMaquinaMulti(infos: List<PrepaqueteSeccionDTO>, maquina: Maquina,descripcion:String){
        val idsOrden = infos.map { it.idOrden }
        val idsOrdenDistinto = infos.map{it.idOrden}.distinct()

        if(idsOrden.size != idsOrdenDistinto.size){
            val infosSeleccionados = infos.filter { it.descripcion == descripcion }

            val idsTareas = infosSeleccionados.map{it.idTarea}.distinct()

            for(id in idsTareas){
                asociarTareaEjecucion(
                    id,
                    infosSeleccionados.first().agrupacion,
                    maquina
                )
            }
            asociarPrepaquete(infosSeleccionados.first(), maquina)

        }
    }

    private fun programarBarquilla(msg: String) {
        if (codigoMaquina == null || codigoMaquina == "") {
            buzzer?.start()
            (frgLog as LogFragment).log("Primero seleccionar una máquina", false)
            return
        } else {
            val codMaquina = this.codigoMaquina

            val serviceMaquina = MaquinaService()
            val callMaquina = serviceMaquina.findMaquinaByCodigoEtiqueta(codMaquina)

            callMaquina.enqueue(object : Callback<Maquina> {
                override fun onFailure(call: Call<Maquina>, t: Throwable) {
                    codigoMaquina = ""
                }

                override fun onResponse(call: Call<Maquina>, respMaquina: Response<Maquina>) {
                    codigoMaquina = ""
                    if (respMaquina.isSuccessful && respMaquina.body() != null) {
                        val servicioOf = OrdenFabricacionService()
                        val maquinaActual = respMaquina.body()!!
                        val callInfoBarquilla = servicioOf.buscarInformacionPorBarquillaSeccion(msg,maquinaActual.codSeccion)

                        if(maquinaActual.ipAutomata!=null && maquinaActual.posicion>0){
                            callInfoBarquilla.enqueue(object: Callback<List<PrepaqueteSeccionDTO>>{
                                override fun onFailure(
                                    call: Call<List<PrepaqueteSeccionDTO>>,
                                    t: Throwable
                                ) {
                                    buzzer?.start()
                                    (frgLog as LogFragment).log("Error protocolo obtener info barquilla", false)
                                }

                                override fun onResponse(
                                    call: Call<List<PrepaqueteSeccionDTO>>,
                                    response: Response<List<PrepaqueteSeccionDTO>>
                                ) {
                                    if(response.isSuccessful){
                                        asignarAMaquina(response.body()!!, maquinaActual)
                                    }else{
                                        buzzer?.start()
                                        (frgLog as LogFragment).log("Error respuesta obtener info barquilla", false)
                                    }
                                }

                            })
                        }
                        else{
                            buzzer?.start()
                            (frgLog as LogFragment).log("Maquina sin programar IPAUTOMATA", false)
                        }
                    }
                }
            })
        }
    }

    private fun asociarTareaEjecucion(idTarea: Int, agrupacion: Int, maquina: Maquina) {
        tareas.clear()
        adapter?.notifyDataSetChanged()
        var asignacion = AsignacionTareaEjecucion(idTarea, maquina?.id!!, agrupacion, idOperario)
        val servicioMaquina = MaquinaService()
        val call = servicioMaquina.asignarTareaEjecucion(asignacion)
        call.enqueue(object : Callback<List<MaquinaColaTrabajo>> {
            override fun onFailure(call: Call<List<MaquinaColaTrabajo>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<MaquinaColaTrabajo>>,
                response: Response<List<MaquinaColaTrabajo>>
            ) {
                if (response.isSuccessful) {
                    var cola = response.body()!!
                    MqttCliente.colaMaquinaActualizada(maquina!!, cola, ArrayList())
                    tareas.addAll(Utils.agruparColaTrabajo(response.body()!!))
                    adapter?.notifyDataSetChanged()
                }
            }

        })
    }

    private fun asociarPrepaquete(prepaquete: PrepaqueteSeccionDTO, maquina: Maquina) {
        var nombreCliente = prepaquete.nombrecli
        if (nombreCliente.length > 25) {
            nombreCliente = nombreCliente.substring(0, 24)
        }

        MqttCliente.asociarTarea(
            maquina.ipAutomata!!,
            maquina.posicion!!,
            prepaquete.idTarea,
            prepaquete.cantidadFabricar.toInt(),
            prepaquete.codigo,
            prepaquete.codUtillaje,
            prepaquete.idUtillajeTalla,
            prepaquete.talla,
            if (prepaquete.codigoAgrupacion == null) prepaquete.codigoEtiqueta else prepaquete.codigoAgrupacion,
            prepaquete.idOrden,
            prepaquete.idOperacion,
            nombreCliente,
            prepaquete.codigoArticulo,
            prepaquete.productividad,
            Store.ID_OPERARIO
        )

    }


    /**
     * Finaliza el activity
     */
    fun atras(v: View) {
        this.finish()
    }
}

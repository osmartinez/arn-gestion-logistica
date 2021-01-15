package com.arneplant.logisticainterna_kot2

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import com.arneplant.logisticainterna_kot2.adapter.TareaProgramadaAdapter
import com.arneplant.logisticainterna_kot2.delegate.BuscadorFragmentDelegate
import com.arneplant.logisticainterna_kot2.fragment.LogFragment
import com.arneplant.logisticainterna_kot2.model.*
import com.arneplant.logisticainterna_kot2.model.dto.*
import com.arneplant.logisticainterna_kot2.network.MqttCliente
import com.arneplant.logisticainterna_kot2.network_implementation.*
import com.arneplant.logisticainterna_kot2.util.Dialogos
import com.arneplant.logisticainterna_kot2.util.Tipo
import com.arneplant.logisticainterna_kot2.util.Utils
import kotlinx.android.synthetic.main.activity_dejar_en_maquina.*
import kotlinx.android.synthetic.main.activity_dejar_en_maquina.frgLog
import kotlinx.android.synthetic.main.activity_dejar_en_maquina.lista
import kotlinx.android.synthetic.main.activity_programacion_maquina.*
import kotlinx.android.synthetic.main.activity_sacar_de_maquina.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DejarEnMaquinaActivity : AppCompatActivity(), BuscadorFragmentDelegate {
    var adapter: TareaProgramadaAdapter? = null
    var tareas: ArrayList<AgrupacionCola> = ArrayList()
    private var log: LogFragment? = null
    private var maquina: Maquina? = null
    private var ctx: Context? = null
    private var buzzer: MediaPlayer? = null
    private var buzzerMultioperacion: MediaPlayer? = null
    private var idOperario: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dejar_en_maquina)
        val sharedPref =
            this.getSharedPreferences("MEMORIA_INTERNA", Context.MODE_PRIVATE) ?: return
        val defaultValue = null
        val defaultValueInt = 0

        val codigoOperario = sharedPref.getString("OPERARIO_CODIGO", defaultValue)
        val nombreOperario = sharedPref.getString("OPERARIO_NOMBRE", defaultValue)
        idOperario = sharedPref.getInt("OPERARIO_ID", defaultValueInt)

        if (codigoOperario != null && nombreOperario != null) {
            this.title = "${codigoOperario} - Dejar en máquina"
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        this.adapter = TareaProgramadaAdapter(this, this.tareas)
        this.lista.adapter = this.adapter
        this.log = frgLog as LogFragment
        this.ctx = this

        this.buzzer = MediaPlayer.create(this, R.raw.buzzer)
        this.buzzerMultioperacion = MediaPlayer.create(this, R.raw.multioperacion)
    }

    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        when (Utils.getTipo(msg)) {
            Tipo.Maquina -> {
                findMaquina(msg)
                obtenerProgramacionMaquina(msg)
            }
            Tipo.PrePaquete -> {
                //intentarAsociar(msg, 0)
                programar(msg)
            }
            Tipo.PrePaqueteAgrupacion -> {
                //intentarAsociar(msg, 1)
                programar(msg)
            }
            else -> {
            }
        }
    }

    private fun programar(cod: String) {
        if (this.maquina == null) {
            Dialogos.mostrarDialogoInformacion("Primero selecciona una maquina", ctx!!)
            return
        } else {
            val servicePrepaquete = PrepaqueteService()
            val callPrepaquete =
                servicePrepaquete.buscarEnSeccion(cod, this.maquina?.codSeccion!!)


            if (this.maquina?.posicion!! > 0 && this.maquina?.ipAutomata != null) {
                callPrepaquete.enqueue(object : Callback<List<PrepaqueteSeccionDTO>> {
                    override fun onFailure(
                        call: Call<List<PrepaqueteSeccionDTO>>,
                        t: Throwable
                    ) {
                        (frgLog as LogFragment).log("Error en la petición", false)
                        buzzer?.start()
                    }

                    override fun onResponse(
                        call: Call<List<PrepaqueteSeccionDTO>>,
                        response: Response<List<PrepaqueteSeccionDTO>>
                    ) {
                        if (response.body() != null) {
                            when {
                                response.body()!!.size == 1 -> {
                                    // 1 operacion 1 of
                                    asociarPrepaquete(response.body()!![0])
                                    asociarTareaEjecucion(response.body()!![0].idTarea.toString(),0)
                                }

                                response.body()!!.size > 1 -> {
                                    // multioperacion
                                    data class Key(val codigo: String, val idOperacion: Int)

                                    fun PrepaqueteSeccionDTO.toKey() =
                                        Key(this.codigo, this.idOperacion)

                                    val gruposOf = response.body()!!.groupBy { it.codigo }
                                    if (gruposOf.size == 1) {
                                        // multioperacion 1 of
                                        buzzerMultioperacion?.start()
                                        Dialogos.mostrarDialogoMultiOperacionAsociar(
                                            gruposOf.toList().first().second,
                                            ::asociarPrepaquete,
                                            ctx!!
                                        )
                                    } else {
                                        // multioperacion agrupacion
                                        var primerGrupo = gruposOf.toList().first()
                                        var operaciones =
                                            primerGrupo.second.groupBy { it.idOperacion }
                                        if (operaciones.size == 1) {
                                            // agrupacion simple
                                            asociarPrepaquete(primerGrupo.second[0])
                                            asociarTareaEjecucionAgrupacion(gruposOf)
                                        } else {
                                            // agrupacion multiple
                                            buzzerMultioperacion?.start()
                                            Dialogos.mostrarDialogoMultiOperacionAsociar(
                                                operaciones.toList().first().second,
                                                ::asociarPrepaquete,
                                                ctx!!
                                            )
                                        }
                                    }
                                }
                                else -> {
                                    buzzer?.start()
                                    (frgLog as LogFragment).log("Etiqueta no existente", false)
                                }
                            }
                        }
                    }

                })

            }
        }
    }

    private fun asociarTareaEjecucion(idsTareas: String, agrupacion: Int){
        tareas.clear()
        adapter?.notifyDataSetChanged()
        var asignacion = AsignacionTareaEjecucion(idsTareas,maquina?.id!!,agrupacion,idOperario)
        val servicioMaquina = MaquinaService()
        val call = servicioMaquina.asignarTareaEjecucion(asignacion)
        call.enqueue(object:Callback<List<MaquinaColaTrabajo>>{
            override fun onFailure(call: Call<List<MaquinaColaTrabajo>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<MaquinaColaTrabajo>>, response: Response<List<MaquinaColaTrabajo>>) {
                if(response.isSuccessful){
                    var cola = response.body()!!
                    MqttCliente.colaMaquinaActualizada(cola)
                    tareas.addAll(Utils.agruparColaTrabajo(response.body()!!))
                    adapter?.notifyDataSetChanged()
                }
            }

        })
    }

    private fun asociarTareaEjecucionAgrupacion (grupos: Map<String, List<PrepaqueteSeccionDTO>>){
        var agrupacion = 0
        var listaIdsTareas = HashSet<Int>()
        for(grupo in grupos){
            for(pre in grupo.value){
                listaIdsTareas.add(pre.idTarea)
                agrupacion = pre.agrupacion
            }
        }
        var idsTareas = ""
        for(id in listaIdsTareas){
            idsTareas+="${id},"
        }
        idsTareas = idsTareas.dropLast(1)

        asociarTareaEjecucion(idsTareas,agrupacion)
    }

    private fun asociarPrepaquete(prepaquete: PrepaqueteSeccionDTO) {
        var nombreCliente = prepaquete.nombrecli
        if(nombreCliente.length> 25){
            nombreCliente = nombreCliente.substring(0,24)
        }

        MqttCliente.asociarTarea(
            maquina?.ipAutomata!!,
            maquina?.posicion!!,
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
            idOperario
        )

    }

    /**
     * Busca una máquina y seguidamente carga todas las tareas programadas
     * en dicha máquina
     */
    private fun findMaquina(cod: String) {
        tareas.clear()
        adapter?.notifyDataSetChanged()
        val serviceMaquina = MaquinaService()
        val callMaquina = serviceMaquina.findMaquinaByCodigoEtiqueta(cod)
        callMaquina.enqueue(object : Callback<Maquina> {
            override fun onFailure(call: Call<Maquina>, t: Throwable) {
                Dialogos.mostrarDialogoInformacion(t.message ?: "Error en la petición", ctx!!)
                title = "Programación Máquina"
                maquina = null
            }

            override fun onResponse(call: Call<Maquina>, response: Response<Maquina>) {
                title = response.body()!!.nombre
                maquina = response.body()
            }
        })

    }

    fun obtenerProgramacionMaquina(codMaquina: String) {
        tareas.clear()
        adapter?.notifyDataSetChanged()
        val service = MaquinaService()
        val call = service.verColaTrabajoPorCodigo(codMaquina)
        call.enqueue(object : Callback<List<MaquinaColaTrabajo>> {
            override fun onFailure(call: Call<List<MaquinaColaTrabajo>>, t: Throwable) {
                Dialogos.mostrarDialogoInformacion(t.message ?: "Error en la petición", ctx!!)

            }

            override fun onResponse(
                call: Call<List<MaquinaColaTrabajo>>,
                response: Response<List<MaquinaColaTrabajo>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                   tareas.addAll(Utils.agruparColaTrabajo(response.body()!!))
                    adapter?.notifyDataSetChanged()
                }
            }

        })
    }

    /**
     * Finaliza el activity
     */
    fun atras(v: View) {
        this.finish()
    }
}

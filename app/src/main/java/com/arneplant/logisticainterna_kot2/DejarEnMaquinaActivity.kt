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

        this.runOnUiThread(Runnable {
            //Cambiar controles
        })


    }

    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        when (Utils.getTipo(msg)) {
            Tipo.Maquina -> {
                this.codigoMaquina = msg
            }
            Tipo.PrePaquete -> {
                this.programar(msg)
            }
            Tipo.PrePaqueteAgrupacion -> {
                this.programar(msg)
            }
            else -> {
            }
        }
    }

    private fun programar(cod: String) {
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
                        val maquina = respMaquina.body()!!
                        val servicePrepaquete = PrepaqueteService()
                        val callPrepaquete =
                            servicePrepaquete.buscarEnSeccion(cod, maquina?.codSeccion!!)


                        if (maquina?.posicion!! > 0 && maquina?.ipAutomata != null) {
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
                                                asociarPrepaquete(response.body()!![0], maquina)
                                                asociarTareaEjecucion(
                                                    response.body()!![0].idTarea.toString(),
                                                    0,
                                                    maquina
                                                )
                                            }

                                            response.body()!!.size > 1 -> {
                                                // multioperacion
                                                data class Key(
                                                    val codigo: String,
                                                    val idOperacion: Int
                                                )

                                                fun PrepaqueteSeccionDTO.toKey() =
                                                    Key(this.codigo, this.idOperacion)

                                                val gruposOf =
                                                    response.body()!!.groupBy { it.codigo }
                                                if (gruposOf.size == 1) {
                                                    // multioperacion 1 of
                                                    buzzerMultioperacion?.start()
                                                    Dialogos.mostrarDialogoMultiOperacionAsociar(
                                                        gruposOf.toList().first().second,
                                                        maquina,
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
                                                        asociarPrepaquete(
                                                            primerGrupo.second[0],
                                                            maquina
                                                        )
                                                        asociarTareaEjecucionAgrupacion(
                                                            gruposOf,
                                                            maquina
                                                        )
                                                    } else {
                                                        // agrupacion multiple
                                                        buzzerMultioperacion?.start()
                                                        Dialogos.mostrarDialogoMultiOperacionAsociar(
                                                            operaciones.toList().first().second,
                                                            maquina,
                                                            ::asociarPrepaquete,
                                                            ctx!!
                                                        )
                                                    }
                                                }
                                            }
                                            else -> {
                                                buzzer?.start()
                                                (frgLog as LogFragment).log(
                                                    "Etiqueta no existente",
                                                    false
                                                )
                                            }
                                        }
                                    }
                                }

                            })

                        }
                    }
                }

            })


        }
    }

    private fun asociarTareaEjecucion(idsTareas: String, agrupacion: Int, maquina: Maquina) {
        tareas.clear()
        adapter?.notifyDataSetChanged()
        var asignacion = AsignacionTareaEjecucion(idsTareas, maquina?.id!!, agrupacion, idOperario)
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

    private fun asociarTareaEjecucionAgrupacion(
        grupos: Map<String, List<PrepaqueteSeccionDTO>>,
        maquina: Maquina
    ) {
        var agrupacion = 0
        var listaIdsTareas = HashSet<Int>()
        for (grupo in grupos) {
            for (pre in grupo.value) {
                listaIdsTareas.add(pre.idTarea)
                agrupacion = pre.agrupacion
            }
        }
        var idsTareas = ""
        for (id in listaIdsTareas) {
            idsTareas += "${id},"
        }
        idsTareas = idsTareas.dropLast(1)

        asociarTareaEjecucion(idsTareas, agrupacion, maquina)
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
            idOperario
        )

    }

    /**
     * Busca una máquina y seguidamente carga todas las tareas programadas
     * en dicha máquina
     */
    private fun findMaquina(cod: String): Maquina? {

        val serviceMaquina = MaquinaService()
        val callMaquina = serviceMaquina.findMaquinaByCodigoEtiqueta(cod)

        var resp = callMaquina.execute()
        if (resp.isSuccessful) {
            return resp.body()
        }

        return null

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

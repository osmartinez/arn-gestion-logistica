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
import com.arneplant.logisticainterna_kot2.delegate.DesprogramarDelegate
import com.arneplant.logisticainterna_kot2.delegate.ReposicionarTareaColaDelegate
import com.arneplant.logisticainterna_kot2.fragment.LogFragment
import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.MaquinaColaTrabajo
import com.arneplant.logisticainterna_kot2.model.dto.*
import com.arneplant.logisticainterna_kot2.network.MqttCliente
import com.arneplant.logisticainterna_kot2.network_implementation.MaquinaService
import com.arneplant.logisticainterna_kot2.network_implementation.OrdenFabricacionService
import com.arneplant.logisticainterna_kot2.network_implementation.PrepaqueteService
import com.arneplant.logisticainterna_kot2.network_implementation.UbicacionService
import com.arneplant.logisticainterna_kot2.util.Dialogos
import com.arneplant.logisticainterna_kot2.util.Tipo
import com.arneplant.logisticainterna_kot2.util.Utils
import kotlinx.android.synthetic.main.activity_dejar_en_maquina.*
import kotlinx.android.synthetic.main.activity_programacion_maquina.frgLog
import kotlinx.android.synthetic.main.activity_programacion_maquina.lista
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProgramacionMaquinaActivity : AppCompatActivity(), BuscadorFragmentDelegate,
    DesprogramarDelegate {


    private var log: LogFragment? = null
    private var tareas: ArrayList<AgrupacionCola> = ArrayList()
    private var adapter: TareaProgramadaAdapter? = null
    private var maquina: Maquina? = null
    private var ctx: Context? = null
    private var buzzer: MediaPlayer? = null
    private var buzzerMultioperacion: MediaPlayer? = null
    private var idOperario: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programacion_maquina)
        this.adapter = TareaProgramadaAdapter(this, this.tareas)
        this.lista.adapter = this.adapter
        this.title = "Programación Máquina"
        this.log = frgLog as LogFragment
        this.ctx = this
        this.adapter?.setReposicionDelegate(object : ReposicionarTareaColaDelegate {
            override fun subir(tarea: AgrupacionCola) {
                (ctx as ProgramacionMaquinaActivity).subir(tarea)
            }

            override fun bajar(tarea: AgrupacionCola) {
                (ctx as ProgramacionMaquinaActivity).bajar(tarea)
            }

        })

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
            val intent = Intent(this, LoginFragment::class.java)
            startActivity(intent)
            this.finish()
        }

        this.buzzer = MediaPlayer.create(this, R.raw.buzzer)
        this.buzzerMultioperacion = MediaPlayer.create(this, R.raw.multioperacion)
    }

    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        when (Utils.getTipo(msg)) {
            Tipo.Maquina -> {
                findMaquina(msg) // carga - descarga
                findProgramacionMaquina(msg)
            }
            Tipo.Barquilla -> {
                programar(msg)
                ubicar(msg)
            }
            Tipo.Utillaje -> {

            }
            else -> {
            }

        }
    }

    private fun ubicar(cod: String) {
        if (maquina == null) {
            buzzer?.start()
            (frgLog as LogFragment).log("Primero ficha maquina", false)
            return
        }

        val servicioUbicacion = UbicacionService()
        val call = servicioUbicacion.ubicarBarquilla(BodyUbicar(cod, this.maquina?.codUbicacion))
        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                buzzer?.start()
                (frgLog as LogFragment).log("Error protocolo ubicar barquilla", false)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (!response.isSuccessful) {
                    buzzer?.start()
                    (frgLog as LogFragment).log("Error respuesta ubicar", false)
                }
            }

        })
    }

    private fun programar(cod: String) {
        if (maquina == null) {
            buzzer?.start()
            (frgLog as LogFragment).log("Primero ficha maquina", false)
            return
        }
        val servicioOf = OrdenFabricacionService()
        val serviceMaquina = MaquinaService()

        val maquinaActual = this.maquina
        val callInfoBarquilla =
            servicioOf.buscarInformacionPorBarquillaSeccion(cod, maquinaActual!!.codSeccion)

        if (maquinaActual?.ipAutomata != null && maquinaActual?.posicion > 0) {
            callInfoBarquilla.enqueue(object : Callback<List<PrepaqueteSeccionDTO>> {
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
                    if (response.isSuccessful && response.body() != null) {
                        val infos = response.body()!!
                        val posNueva =
                            if (tareas.isEmpty()) 1 else tareas.maxBy { it.posicion }!!.posicion + 1
                        for (info in infos) {
                            // programar
                            val callProgramar = serviceMaquina.programarTareaCola(
                                AsignacionTareaProgramacion(
                                    info.idTarea,
                                    maquina?.id!!,
                                    if (info.agrupacion == null) 0 else info.agrupacion,
                                    posNueva,
                                    Store.ID_OPERARIO,
                                    cod
                                )

                            )
                            callProgramar.enqueue(object : Callback<List<MaquinaColaTrabajo>> {
                                override fun onFailure(
                                    call: Call<List<MaquinaColaTrabajo>>,
                                    t: Throwable
                                ) {
                                    buzzer?.start()
                                    (frgLog as LogFragment).log(
                                        "Error protocolo obtener info barquilla",
                                        false
                                    )
                                }

                                override fun onResponse(
                                    call: Call<List<MaquinaColaTrabajo>>,
                                    response: Response<List<MaquinaColaTrabajo>>
                                ) {
                                    MqttCliente.colaMaquinaActualizada(
                                        maquina!!,
                                        response.body()!!,
                                        ArrayList()
                                    )
                                    addTrabajos(response.body()!!)
                                }
                            })
                        }
                    } else {
                        buzzer?.start()
                        (frgLog as LogFragment).log("Error respuesta obtener info barquilla", false)
                    }
                }

            })
        }
    }

    private fun addTrabajos(trabajos: List<MaquinaColaTrabajo>) {
        tareas.clear()
        tareas.addAll(Utils.agruparColaTrabajo(trabajos))
        adapter?.notifyDataSetChanged()
    }

    private fun subir(agrupacion: AgrupacionCola) {

        // restar posicion de las tareas que voy a subir
        //
        val posActual = agrupacion.posicion

        val tareasAnteriores = this.tareas.firstOrNull { x -> x.posicion == posActual - 1 }
        val service = MaquinaService()

        if (tareasAnteriores == null) {

        } else {
            val nuevaPos = agrupacion.posicion - 1

            val call = service.actualizarPosicionTarea(
                AsignacionTareaProgramacion(
                    agrupacion.tareas.first().idTarea,
                    maquina?.id!!,
                    agrupacion.tareas.first().agrupacion,
                    nuevaPos,
                    Store.ID_OPERARIO,
                    agrupacion.codigoEtiqueta
                )
            )


            call.enqueue(object : Callback<List<MaquinaColaTrabajo>> {
                override fun onFailure(call: Call<List<MaquinaColaTrabajo>>, t: Throwable) {
                    log!!.log(t.message ?: "Error de protocolo", false)
                }

                override fun onResponse(
                    call: Call<List<MaquinaColaTrabajo>>,
                    response: Response<List<MaquinaColaTrabajo>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        MqttCliente.colaMaquinaActualizada(
                            maquina!!,
                            response.body()!!,
                            ArrayList()
                        )

                        tareas.clear()
                        tareas.addAll(Utils.agruparColaTrabajo(response.body()!!))
                        adapter?.notifyDataSetChanged()


                    }
                }

            })

        }


    }

    private fun bajar(agrupacion: AgrupacionCola) {
        // restar posicion de las tareas que voy a subir
        //
        val posActual = agrupacion.posicion

        val tareasPosteriores = this.tareas.firstOrNull { x -> x.posicion == posActual + 1 }
        val service = MaquinaService()

        if (tareasPosteriores == null) {
        } else {
            val nuevaPos = agrupacion.posicion + 1
            val call = service.actualizarPosicionTarea(
                AsignacionTareaProgramacion(
                    agrupacion.tareas.first().idTarea,
                    maquina?.id!!,
                    agrupacion.tareas.first().agrupacion,
                    nuevaPos,
                    Store.ID_OPERARIO,
                    agrupacion.codigoEtiqueta
                )
            )


            call.enqueue(object : Callback<List<MaquinaColaTrabajo>> {
                override fun onFailure(call: Call<List<MaquinaColaTrabajo>>, t: Throwable) {
                    log!!.log(t.message ?: "Error de protocolo", false)
                }

                override fun onResponse(
                    call: Call<List<MaquinaColaTrabajo>>,
                    response: Response<List<MaquinaColaTrabajo>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        MqttCliente.colaMaquinaActualizada(
                            maquina!!,
                            response.body()!!,
                            ArrayList()
                        )

                        tareas.clear()
                        tareas.addAll(Utils.agruparColaTrabajo(response.body()!!))
                        adapter?.notifyDataSetChanged()


                    }
                }

            })
        }


    }

    override fun desprogramar(agrupacion: AgrupacionCola) {
        tareas.clear()
        adapter?.notifyDataSetChanged()
        val service = MaquinaService()

        val asignacion =
            AsignacionTareaEjecucion(agrupacion.tareas.first().idTarea, maquina?.id!!, agrupacion.agrupacion, 0)
        val call = service.desasignarTareaEjecucion(asignacion)
        call.enqueue(object : Callback<List<MaquinaColaTrabajo>> {
            override fun onFailure(call: Call<List<MaquinaColaTrabajo>>, t: Throwable) {
                log!!.log(t.message ?: "Error de protocolo", false)
            }

            override fun onResponse(
                call: Call<List<MaquinaColaTrabajo>>,
                response: Response<List<MaquinaColaTrabajo>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    var cola = response.body()!!
                    MqttCliente.colaMaquinaActualizada(maquina!!, cola, agrupacion.tareas)
                    tareas.clear()
                    tareas.addAll(Utils.agruparColaTrabajo(cola))
                    adapter?.notifyDataSetChanged()

                }
            }

        })

    }


    private fun findMaquina(cod: String) {
        val serviceMaquina = MaquinaService()
        val callMaquina = serviceMaquina.findMaquinaByCodigoEtiqueta(cod)
        callMaquina.enqueue(object : Callback<Maquina> {
            override fun onFailure(call: Call<Maquina>, t: Throwable) {
                log!!.log(t.message ?: "Error en la petición", false)
                title = "Programación Máquina"
                maquina = null
            }

            override fun onResponse(call: Call<Maquina>, response: Response<Maquina>) {
                if (response.body() != null) {
                    title = response.body()!!.nombre
                    maquina = response.body()

                }
            }

        })
    }

    private fun findProgramacionMaquina(cod: String) {
        tareas.clear()
        adapter?.notifyDataSetChanged()

        val service = MaquinaService()
        val call = service.verColaTrabajoPorCodigo(cod)
        call.enqueue(object : Callback<List<MaquinaColaTrabajo>> {
            override fun onFailure(call: Call<List<MaquinaColaTrabajo>>, t: Throwable) {
                log!!.log(t.message ?: "Error en la petición", false)

            }

            override fun onResponse(
                call: Call<List<MaquinaColaTrabajo>>,
                response: Response<List<MaquinaColaTrabajo>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    tareas.clear()
                    tareas.addAll(Utils.agruparColaTrabajo(response.body()!!))
                    adapter?.notifyDataSetChanged()
                }
            }

        })
    }

    fun atras(v: View) {
        this.finish()
    }

}

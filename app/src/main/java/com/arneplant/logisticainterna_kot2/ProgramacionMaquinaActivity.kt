package com.arneplant.logisticainterna_kot2

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arneplant.logisticainterna_kot2.adapter.TareaProgramadaAdapter
import com.arneplant.logisticainterna_kot2.delegate.BuscadorFragmentDelegate
import com.arneplant.logisticainterna_kot2.delegate.DesprogramarDelegate
import com.arneplant.logisticainterna_kot2.fragment.LogFragment
import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.MaquinaColaTrabajo
import com.arneplant.logisticainterna_kot2.model.TareaPendiente
import com.arneplant.logisticainterna_kot2.model.dto.AgrupacionCola
import com.arneplant.logisticainterna_kot2.model.dto.AsignacionTareaEjecucion
import com.arneplant.logisticainterna_kot2.model.dto.AsignacionTareaProgramacion
import com.arneplant.logisticainterna_kot2.model.dto.PrepaqueteSeccionDTO
import com.arneplant.logisticainterna_kot2.network.MqttCliente
import com.arneplant.logisticainterna_kot2.network_implementation.GestionPaqueteMaquina
import com.arneplant.logisticainterna_kot2.network_implementation.MaquinaService
import com.arneplant.logisticainterna_kot2.network_implementation.PrepaqueteService
import com.arneplant.logisticainterna_kot2.network_implementation.TareaProgramadaService
import com.arneplant.logisticainterna_kot2.util.Dialogos
import com.arneplant.logisticainterna_kot2.util.Tipo
import com.arneplant.logisticainterna_kot2.util.Utils
import kotlinx.android.synthetic.main.activity_dejar_en_maquina.*
import kotlinx.android.synthetic.main.activity_programacion_maquina.*
import kotlinx.android.synthetic.main.activity_programacion_maquina.frgLog
import kotlinx.android.synthetic.main.activity_programacion_maquina.lista
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class ProgramacionMaquinaActivity : AppCompatActivity(), BuscadorFragmentDelegate , DesprogramarDelegate{


    private var log: LogFragment? = null
    private var tareas : ArrayList<AgrupacionCola> = ArrayList()
    private var adapter: TareaProgramadaAdapter?  = null
    private var maquina: Maquina? = null
    private var ctx: Context? = null
    private var buzzer: MediaPlayer? = null
    private var buzzerMultioperacion: MediaPlayer? = null
    private var idOperario: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programacion_maquina)
        this.adapter = TareaProgramadaAdapter(this,this.tareas)
        this.lista.adapter = this.adapter
        this.title = "Programación Máquina"
        this.log = frgLog as LogFragment
        this.ctx = this

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

        this.buzzer = MediaPlayer.create(this, R.raw.buzzer)
        this.buzzerMultioperacion = MediaPlayer.create(this, R.raw.multioperacion)
    }

    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        when (Utils.getTipo(msg)) {
            Tipo.Maquina -> {
                findMaquina(msg) // carga - descarga
                findProgramacionMaquina(msg)
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
                                    // simple
                                    programarPrepaquete(response.body()!![0])
                                }

                                response.body()!!.size > 1 -> {
                                    // agrupacion
                                    data class Key(val codigo: String, val idOperacion: Int)

                                    fun PrepaqueteSeccionDTO.toKey() =
                                        Key(this.codigo, this.idOperacion)

                                    val gruposOf = response.body()!!.groupBy { it.codigo }
                                    if (gruposOf.size == 1) {
                                        // s multi
                                        buzzerMultioperacion?.start()
                                        Dialogos.mostrarDialogoMultiOperacionAsociar(
                                            gruposOf.toList().first().second,
                                            ::programarPrepaquete,
                                            ctx!!
                                        )
                                    } else {
                                        // varias ofs
                                        // cojo la primera
                                        var primerGrupo = gruposOf.toList().first()
                                        // agrupo sus operaciones
                                        var operaciones =
                                            primerGrupo.second.groupBy { it.idOperacion }
                                        // varias ofs pero con 1 operacion
                                        if (operaciones.size == 1) {
                                            // agrupacion simple
                                            programarPrepaquetes(response.body()!!)
                                        } else {
                                            // agrupacion multiple
                                            // ESTO ESTA MAL HAY QUE CORREGIRLOO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                            buzzerMultioperacion?.start()
                                            Dialogos.mostrarDialogoMultiOperacionAsociar(
                                                operaciones.toList().first().second,
                                                ::programarPrepaquete,
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

    private fun programarColaMaquina(idsTareas: String, agrupacion: Int){
        tareas.clear()
        adapter?.notifyDataSetChanged()
        var asignacion = AsignacionTareaProgramacion(idsTareas,maquina?.id!!,agrupacion,idOperario)
        val servicioMaquina = MaquinaService()
        val call = servicioMaquina.programarTareaCola(asignacion)
        call.enqueue(object:Callback<List<MaquinaColaTrabajo>>{
            override fun onFailure(call: Call<List<MaquinaColaTrabajo>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<MaquinaColaTrabajo>>, response: Response<List<MaquinaColaTrabajo>>) {
                if(response.isSuccessful){
                    var cola = response.body()!!
                    MqttCliente.colaMaquinaActualizada(maquina!!,cola,ArrayList())
                    tareas.addAll(Utils.agruparColaTrabajo(cola))
                    adapter?.notifyDataSetChanged()
                }
            }

        })
    }

    private fun programarPrepaquete(pre: PrepaqueteSeccionDTO){
        programarColaMaquina(pre.idTarea.toString(), 0)
    }

    private fun programarPrepaquetes(pres: List<PrepaqueteSeccionDTO>){
        var idsTareas = ""
        for(pre in pres){
            idsTareas+= "${pre.idTarea},"
        }
        programarColaMaquina(idsTareas, pres.first().agrupacion)
    }

    override fun desprogramar(agrupacion: AgrupacionCola) {
        tareas.clear()
        adapter?.notifyDataSetChanged()
        var idsTareas = ""
        for(t in agrupacion.tareas){
            idsTareas+="${t.idTarea},"
        }
        val asignacion = AsignacionTareaEjecucion(idsTareas,maquina?.id!!,agrupacion.agrupacion,0)
        val service = MaquinaService()
        val call = service.desasignarTareaEjecucion(asignacion)
        call.enqueue(object: Callback<List<MaquinaColaTrabajo>>{
            override fun onFailure(call: Call<List<MaquinaColaTrabajo>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<MaquinaColaTrabajo>>,
                response: Response<List<MaquinaColaTrabajo>>
            ) {
                if(response.isSuccessful && response.body()!=null){
                    var cola = response.body()!!
                    MqttCliente.colaMaquinaActualizada(maquina!!,cola,agrupacion.tareas)
                    tareas.addAll(Utils.agruparColaTrabajo(cola))
                    adapter?.notifyDataSetChanged()

                }
            }

        })
    }
    private fun findMaquina(cod: String){
        val serviceMaquina = MaquinaService()
        val callMaquina = serviceMaquina.findMaquinaByCodigoEtiqueta(cod)
        callMaquina.enqueue(object: Callback<Maquina>{
            override fun onFailure(call: Call<Maquina>, t: Throwable) {
                log!!.log(t.message ?: "Error en la petición", false)
                title = "Programación Máquina"
                maquina= null
            }

            override fun onResponse(call: Call<Maquina>, response: Response<Maquina>) {
                if(response.body()!=null){
                    title = response.body()!!.nombre
                    maquina = response.body()

                }
            }

        })
    }

    private fun findProgramacionMaquina(cod: String){
        tareas.clear()
        adapter?.notifyDataSetChanged()

        val service = MaquinaService()
        val call = service.verColaTrabajoPorCodigo(cod)
        call.enqueue(object: Callback<List<MaquinaColaTrabajo>> {
            override fun onFailure(call: Call<List<MaquinaColaTrabajo>>, t: Throwable) {
                log!!.log(t.message ?: "Error en la petición", false)

            }

            override fun onResponse(
                call: Call<List<MaquinaColaTrabajo>>,
                response: Response<List<MaquinaColaTrabajo>>
            ) {
                if(response.isSuccessful && response.body()!=null){
                    tareas.addAll(Utils.agruparColaTrabajo(response.body()!!))
                    adapter?.notifyDataSetChanged()
                }
            }

        })
    }

    fun atras(v: View){
        this.finish()
    }

}

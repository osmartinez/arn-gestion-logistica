package com.arneplant.logisticainterna_kot2

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arneplant.logisticainterna_kot2.adapter.UbicacionAdapter
import com.arneplant.logisticainterna_kot2.application.Store
import com.arneplant.logisticainterna_kot2.delegate.BuscadorFragmentDelegate
import com.arneplant.logisticainterna_kot2.fragment.LogFragment
import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.MaquinaColaTrabajo
import com.arneplant.logisticainterna_kot2.model.Ubicacion
import com.arneplant.logisticainterna_kot2.model.UbicacionPaquetes
import com.arneplant.logisticainterna_kot2.model.dto.*
import com.arneplant.logisticainterna_kot2.network.MqttCliente
import com.arneplant.logisticainterna_kot2.network_implementation.MaquinaService
import com.arneplant.logisticainterna_kot2.network_implementation.OrdenFabricacionService
import com.arneplant.logisticainterna_kot2.network_implementation.UbicacionService
import com.arneplant.logisticainterna_kot2.network_implementation.UtillajeService
import com.arneplant.logisticainterna_kot2.util.Tipo
import com.arneplant.logisticainterna_kot2.util.Utils
import kotlinx.android.synthetic.main.activity_ubicar.frgLog
import kotlinx.android.synthetic.main.activity_ubicar.lista

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UbicarActivity : AppCompatActivity(), BuscadorFragmentDelegate {

    var buzzer: MediaPlayer? = null
    var ubicacionesPaquetes: ArrayList<UbicacionPaquetes> = ArrayList()
    var ubicacion: UbicacionPaquetes? = null
    var maquina: Maquina? = null
    var adapter: UbicacionAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicar)
        this.title = "Ubicar"
        this.buzzer = MediaPlayer.create(this, R.raw.buzzer)
        this.adapter = UbicacionAdapter(this, this.ubicacionesPaquetes)
        this.lista.adapter = this.adapter
    }

    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        when (Utils.getTipo(msg)) {
            Tipo.Ubicacion -> {
                findUbicacion(msg)
            }
            Tipo.Maquina -> {
                findMaquina(msg)
            }
            Tipo.Barquilla -> {
                ubicarBarquilla(msg)
            }

            Tipo.Utillaje -> {
                ubicarUtillaje(msg)
            }
        }
    }

    private fun ubicarBarquilla(cod: String) {
        if (this.ubicacion == null) {
            (frgLog as LogFragment).log("Escanea una ubicación", false)
            buzzer?.start()
        } else {
            val service = UbicacionService()
            val codUbicacion = this.ubicacion?.codUbicacion!!
            val call = service.ubicarBarquilla(BodyUbicar(cod, codUbicacion))
            call.enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    (frgLog as LogFragment).log("Error de protocolo", false)
                    buzzer?.start()
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        actualizarUbicacion(cod, codUbicacion)
                    }
                }
            })

            // programar si y solo si la ubicacion es maquina
            // suponemos que si la ubicacion es maquina han llegado a traves de un codigo de
            // etiqueta de tipo maquina y por tanto la maquina está seteada
            if (this.ubicacion?.isEsMaquina!!) {

                val servicioOf = OrdenFabricacionService()
                val serviceMaquina = MaquinaService()

                val maquinaActual = this.maquina!!
                val callInfoBarquilla =
                    servicioOf.buscarInformacionPorBarquillaSeccion(cod, maquinaActual!!.codSeccion)

                if (maquinaActual?.ipAutomata != null && maquinaActual?.posicion > 0) {
                    callInfoBarquilla.enqueue(object : Callback<List<PrepaqueteSeccionDTO>> {
                        override fun onFailure(
                            call: Call<List<PrepaqueteSeccionDTO>>,
                            t: Throwable
                        ) {
                            buzzer?.start()
                            (frgLog as LogFragment).log(
                                "Error protocolo obtener info barquilla",
                                false
                            )
                        }

                        override fun onResponse(
                            call: Call<List<PrepaqueteSeccionDTO>>,
                            response: Response<List<PrepaqueteSeccionDTO>>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                val infos = response.body()!!
                                for (info in infos) {
                                    // programar
                                    val callProgramar = serviceMaquina.programarTareaCola(
                                        AsignacionTareaProgramacion(
                                            info.idTarea,
                                            maquina?.id!!,
                                            if (info.agrupacion == null) 0 else info.agrupacion,
                                            0,
                                            Store.ID_OPERARIO,
                                            cod
                                        )

                                    )
                                    callProgramar.enqueue(object :
                                        Callback<List<MaquinaColaTrabajo>> {
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

                                        }
                                    })
                                }
                            } else {
                                buzzer?.start()
                                (frgLog as LogFragment).log(
                                    "Error respuesta obtener info barquilla",
                                    false
                                )
                            }
                        }

                    })
                }
            }
        }


    }


    private fun ubicarUtillaje(cod: String) {
        if (this.ubicacion == null) {
            (frgLog as LogFragment).log("Escanea una ubicación", false)
            buzzer?.start()

        } else {
            val service = UtillajeService()
            val utillajeUbicacion = UtillajeUbicacion()
            utillajeUbicacion.codUbicacion = this.ubicacion?.codUbicacion
            utillajeUbicacion.codigoEtiqueta = cod
            val call = service.ubicarPorEtiqueta(utillajeUbicacion)
            call.enqueue(object : Callback<UtillajesTallasColeccion> {
                override fun onFailure(call: Call<UtillajesTallasColeccion>, t: Throwable) {
                    (frgLog as LogFragment).log("Error de protocolo", false)
                    buzzer?.start()
                }

                override fun onResponse(
                    call: Call<UtillajesTallasColeccion>,
                    response: Response<UtillajesTallasColeccion>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        actualizarUbicacion(response.body()!!)
                    } else {
                        (frgLog as LogFragment).log("Error de respuesta", false)
                        buzzer?.start()
                    }
                }

            })
        }
    }

    private fun findMaquina(cod: String) {
        this.maquina = null
        val service = MaquinaService()
        val call = service.findMaquinaByCodigoEtiqueta(cod)

        call.enqueue(object : Callback<Maquina> {
            override fun onFailure(call: Call<Maquina>, t: Throwable) {
                (frgLog as LogFragment).log("Error de protocolo", false)
                buzzer?.start()
            }

            override fun onResponse(call: Call<Maquina>, response: Response<Maquina>) {
                if (response.isSuccessful && response.body() != null) {
                    maquina = response.body()!!
                    findUbicacion(response.body()!!.codUbicacion)
                } else {
                    (frgLog as LogFragment).log("Error de respuesta", false)
                    buzzer?.start()
                }
            }

        })
    }

    private fun findUbicacion(cod: String) {
        val service = UbicacionService()
        val call = service.getUbicacion(cod)
        call.enqueue(object : Callback<Ubicacion> {
            override fun onFailure(call: Call<Ubicacion>, t: Throwable) {
                (frgLog as LogFragment).log("Error de protocolo", false)
            }

            override fun onResponse(call: Call<Ubicacion>, response: Response<Ubicacion>) {
                if (response.isSuccessful && response.body() != null) {
                    seleccionarUbicacion(response.body()!!)
                } else {
                    (frgLog as LogFragment).log("Error de respuesta", false)
                    buzzer?.start()

                }
            }

        })
    }

    private fun actualizarUbicacion(codEtiqueta: String, codUbicacion: String) {
        for (ub in this.ubicacionesPaquetes) {
            if (ub.codUbicacion == codUbicacion) {
                if (ub.barquillas.firstOrNull { x -> x.codigoEtiqueta == codEtiqueta } == null) {
                    ub.barquillas.add(Barquillas(codEtiqueta, 0, codUbicacion, 0))
                }
            }
        }
        this.adapter?.notifyDataSetChanged()
    }

    private fun actualizarUbicacion(utillajesTallasColeccion: UtillajesTallasColeccion) {
        for (ub in this.ubicacionesPaquetes) {
            if (ub.codUbicacion == utillajesTallasColeccion.codUbicacion) {
                if (ub.utillajes.firstOrNull { x -> x.codigoEtiqueta == utillajesTallasColeccion.codigoEtiqueta } == null) {
                    ub.utillajes.add(utillajesTallasColeccion)
                }
            }
        }
        this.adapter?.notifyDataSetChanged()
    }

    private fun seleccionarUbicacion(ubicacion: Ubicacion) {
        var ubicacionExistente =
            this.ubicacionesPaquetes.firstOrNull { x -> x.codUbicacion.equals(ubicacion.codUbicacion) }

        if (ubicacionExistente != null) {
            this.ubicacionesPaquetes.remove(ubicacionExistente)
            this.ubicacion = ubicacionExistente
        } else {
            this.ubicacion = UbicacionPaquetes(ubicacion)
        }

        for (m in this.ubicacionesPaquetes) {
            m.isSeleccionada = false
        }

        this.ubicacion?.isSeleccionada = true
        this.ubicacionesPaquetes.add(0, this.ubicacion!!)


        this.adapter?.notifyDataSetChanged()
    }

    fun atras(v: View) {
        this.finish()
    }

    fun limpiar(v: View) {
        this.ubicacionesPaquetes.clear()
        this.adapter?.notifyDataSetChanged()
    }

}

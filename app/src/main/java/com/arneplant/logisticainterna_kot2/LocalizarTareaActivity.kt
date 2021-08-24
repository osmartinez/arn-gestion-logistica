package com.arneplant.logisticainterna_kot2

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arneplant.logisticainterna_kot2.adapter.OperacionesBarquillaAdapter
import com.arneplant.logisticainterna_kot2.adapter.TareaUbicacionAdapter
import com.arneplant.logisticainterna_kot2.application.Store
import com.arneplant.logisticainterna_kot2.delegate.BuscadorFragmentDelegate
import com.arneplant.logisticainterna_kot2.delegate.FiltroOperacionCambiadoDelegate
import com.arneplant.logisticainterna_kot2.fragment.LogFragment
import com.arneplant.logisticainterna_kot2.model.OrdenFabricacionOperacion
import com.arneplant.logisticainterna_kot2.model.dto.AgrupacionUbicacionTarea
import com.arneplant.logisticainterna_kot2.model.dto.UbicacionTarea
import com.arneplant.logisticainterna_kot2.network_implementation.OrdenFabricacionService
import com.arneplant.logisticainterna_kot2.util.Tipo
import com.arneplant.logisticainterna_kot2.util.Utils
import kotlinx.android.synthetic.main.activity_dejar_en_maquina.*
import kotlinx.android.synthetic.main.activity_dejar_en_maquina.frgLog
import kotlinx.android.synthetic.main.activity_localizar_tarea.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocalizarTareaActivity : AppCompatActivity(), BuscadorFragmentDelegate {


    private var buzzer: MediaPlayer? = null


    private val operaciones = ArrayList<OrdenFabricacionOperacion>()
    private val ubicaciones = ArrayList<AgrupacionUbicacionTarea>()

    private var adaptadorFiltros: OperacionesBarquillaAdapter? = null
    private var adaptadorUbicaciones: TareaUbicacionAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localizar_tarea)
        this.buzzer = MediaPlayer.create(this, R.raw.buzzer)

        this.adaptadorFiltros = OperacionesBarquillaAdapter(this, operaciones)
        this.adaptadorUbicaciones = TareaUbicacionAdapter(this, ubicaciones)

        this.adaptadorFiltros?.setFiltroCambiadoListener(object : FiltroOperacionCambiadoDelegate {
            override fun seccionSeleccionada(codSeccion: String) {
                Store.CODSECCIONSELECCIONADA_BUSQUEDA = codSeccion
            }

        })
        this.listaFiltrosOperaciones.adapter = adaptadorFiltros
        this.listaUbicaciones.adapter = adaptadorUbicaciones

    }


    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        when (Utils.getTipo(msg)) {
            Tipo.Barquilla -> {
                findOperacionesPorBarquilla(msg)
                findTareaPorBarquilla(msg)
            }
        }
    }

    private fun findOperacionesPorBarquilla(msg: String) {
        operaciones.clear()
        adaptadorFiltros?.notifyDataSetChanged()
        val service = OrdenFabricacionService()
        val call = service.obtenerOperacionesPorBarquilla(msg)
        call.enqueue(object : Callback<List<OrdenFabricacionOperacion>> {
            override fun onFailure(call: Call<List<OrdenFabricacionOperacion>>, t: Throwable) {
                buzzer?.start()
                (frgLog as LogFragment).log("Error de protocolo", false)
            }

            override fun onResponse(
                call: Call<List<OrdenFabricacionOperacion>>,
                response: Response<List<OrdenFabricacionOperacion>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    operaciones.clear()

                    var ops = response.body()!!
                    var agrupadas = ops.groupBy { it.codSeccion }
                    var filtros = ArrayList<OrdenFabricacionOperacion>()
                    for (grupo in agrupadas) {
                        filtros.add(grupo.value.first())
                        if (grupo.value.first().codSeccion == Store.CODSECCIONSELECCIONADA_BUSQUEDA) {
                            grupo.value.first().isSeleccionado = true
                        }
                    }
                    operaciones.addAll(filtros)
                    adaptadorFiltros?.notifyDataSetChanged()
                } else {
                    buzzer?.start()
                    (frgLog as LogFragment).log("Error en la respuesta", false)
                }
            }

        })
    }

    private fun findTareaPorBarquilla(msg: String) {
        ubicaciones.clear()
        adaptadorUbicaciones?.notifyDataSetChanged()
        if (Store.CODSECCIONSELECCIONADA_BUSQUEDA == null || Store.CODSECCIONSELECCIONADA_BUSQUEDA == "") {
            Store.CODSECCIONSELECCIONADA_BUSQUEDA = "110"
            //buzzer?.start()
            //(frgLog as LogFragment).log("Selecciona una operación primero", false)
            //return
        }
        //else {
            (frgLog as LogFragment).log(
                "Buscando en sección " + Store.CODSECCIONSELECCIONADA_BUSQUEDA,
                true
            )

      //  }


        val service = OrdenFabricacionService()
        val call =
            service.obtenerUbicacionesTareaPorBarquilla(msg, Store.CODSECCIONSELECCIONADA_BUSQUEDA)
        call.enqueue(object : Callback<List<UbicacionTarea>> {
            override fun onFailure(call: Call<List<UbicacionTarea>>, t: Throwable) {
                buzzer?.start()
                (frgLog as LogFragment).log("Error de protocolo", false)
            }

            override fun onResponse(
                call: Call<List<UbicacionTarea>>,
                response: Response<List<UbicacionTarea>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    ubicaciones.clear()
                    var ubs = response.body()!!
                    ubs = ubs.sortedBy { !it.isEsMaquina}

                    var ejecucionUbs = ubs.filter { x -> x.isEjecucion }
                    for (ub in ejecucionUbs) {
                        ubs = ubs.dropWhile { x->
                            !x.isEjecucion &&
                                    x.codUbicacion == ub.codUbicacion &&
                                    x.cantidad == ub.cantidad &&
                                    x.numCajas == ub.numCajas &&
                                    x.talla == ub.talla
                        }
                    }

                    for(maquina in ubs.filter { x->x.isEsMaquina }){
                        var listaAux = ArrayList<UbicacionTarea>()
                        listaAux.add(maquina)
                        ubicaciones.add(AgrupacionUbicacionTarea(listaAux))
                    }
                    var agrupadas = ubs.filter { x->!x.isEsMaquina }.groupBy { x->x.descripcion }

                    for(grupo in agrupadas){
                        ubicaciones.add(AgrupacionUbicacionTarea(grupo.value))
                    }

                    adaptadorUbicaciones?.notifyDataSetChanged()
                } else {
                    buzzer?.start()
                    (frgLog as LogFragment).log("Error en la respuesta", false)
                }
            }

        })
    }

    /**
     * finaliza la actividad
     */
    fun atras(v: View) {
        this.finish()
    }

    fun limpiar(v: View) {

    }
}

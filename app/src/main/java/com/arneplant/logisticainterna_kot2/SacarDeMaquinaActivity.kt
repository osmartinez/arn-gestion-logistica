package com.arneplant.logisticainterna_kot2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arneplant.logisticainterna_kot2.adapter.ConsumoMaquinaAdapter
import com.arneplant.logisticainterna_kot2.delegate.BuscadorFragmentDelegate
import com.arneplant.logisticainterna_kot2.delegate.RestaurarConsumosDelegate
import com.arneplant.logisticainterna_kot2.fragment.LogFragment
import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.util.Dialogos
import com.arneplant.logisticainterna_kot2.util.Tipo
import com.arneplant.logisticainterna_kot2.util.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import com.arneplant.logisticainterna_kot2.application.Store
import com.arneplant.logisticainterna_kot2.model.OrdenFabricacionOperacion
import com.arneplant.logisticainterna_kot2.model.dto.*
import com.arneplant.logisticainterna_kot2.network.MqttCliente
import com.arneplant.logisticainterna_kot2.network_implementation.*
import kotlinx.android.synthetic.main.activity_sacar_de_maquina.*
import kotlinx.android.synthetic.main.principal_fragment.view.*
import retrofit2.http.Body


class SacarDeMaquinaActivity : AppCompatActivity(), BuscadorFragmentDelegate,
    RestaurarConsumosDelegate {

    private val sharedPrefFile = "preferencias_notificacion"
    private val NOTIFICAR_TAREA = "NOTIFICAR_TAREA"
    private val NOTIFICAR_NOTA = "NOTIFICAR_NOTA"
    private var adapter: ConsumoMaquinaAdapter? = null
    private var ctx: Context? = null
    private var maquina: Maquina? = null
    private var maquinas = ArrayList<Maquina>()
    private var log: LogFragment? = null
    private var buzzer: MediaPlayer? = null
    private var buzzerTareaAcabada: MediaPlayer? = null
    private var buzzerNotaAcabada: MediaPlayer? = null
    private var buzzerMultioperacion: MediaPlayer? = null
    private var sharedPreferences: SharedPreferences? = null

    private var memoriaConsumo: MemoriaConsumo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sacar_de_maquina)

        this.ctx = this

        if (Store.ID_OPERARIO != 0) {
            this.title = "${Store.CODIGO_OPERARIO} - Sacar de máquina"
        } else {
            this.finish()
        }

        (this as AppCompatActivity).setSupportActionBar(sacar_maquina_toolbar)


        this.adapter = ConsumoMaquinaAdapter(this, maquinas)
        this.lista.adapter = this.adapter
        this.log = frgLog as LogFragment
        this.buzzer = MediaPlayer.create(this, R.raw.buzzer)
        this.buzzerNotaAcabada = MediaPlayer.create(this, R.raw.notacompletada)
        this.buzzerTareaAcabada = MediaPlayer.create(this, R.raw.tareacompletada)
        this.buzzerMultioperacion = MediaPlayer.create(this, R.raw.multioperacion)
        this.sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_sacar_de_maquina, menu)
        if (sharedPreferences == null) {
            this.sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        }
        var itemNotificarTarea = menu.findItem(R.id.notificarTarea)
        itemNotificarTarea.isChecked = sharedPreferences?.getBoolean(NOTIFICAR_TAREA, true)!!
        var itemNotificarNota = menu.findItem(R.id.notificarNota)
        itemNotificarNota.isChecked = sharedPreferences?.getBoolean(NOTIFICAR_NOTA, true)!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.notificarTarea) {
            item.isChecked = (!item.isChecked)
            val editor: SharedPreferences.Editor = sharedPreferences?.edit()!!
            editor.putBoolean(NOTIFICAR_TAREA, item.isChecked)
            editor.apply()
            editor.commit()
            return true
        }
        if (id == R.id.notificarNota) {
            item.isChecked = (!item.isChecked)
            val editor: SharedPreferences.Editor = sharedPreferences?.edit()!!
            editor.putBoolean(NOTIFICAR_NOTA, item.isChecked)
            editor.apply()
            editor.commit()
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    /**
     * Se dispara cuando se lee un código de barras
     * Discrimina el tipo de código de barras y ejecuta la función adecuada
     */
    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        when (Utils.getTipo(msg)) {
            Tipo.Maquina -> {
                findMaquina(msg)
            }
            Tipo.PrePaqueteAgrupacion -> {
                consumirEtiqueta(msg)
            }
            Tipo.PrePaquete -> {
                consumirEtiqueta(msg)
            }
            Tipo.Barquilla -> {
                consumirBarquilla(msg)
            }
        }
    }

    private fun consumirBarquilla(cod: String) {
        if (this.maquina == null) {
            Dialogos.mostrarDialogoInformacion("Primero selecciona una máquina", this)
            buzzer?.start()
            return
        }

        val idMaquina = this.maquina!!.id
        val codSeccion = this.maquina!!.codSeccion
        val serviceOf = OrdenFabricacionService()
        val callOperaciones = serviceOf.buscarOperacionesPorBarquillaSeccion(cod, codSeccion)

        callOperaciones.enqueue(object : Callback<List<OrdenFabricacionOperacion>> {
            override fun onFailure(call: Call<List<OrdenFabricacionOperacion>>, t: Throwable) {
                (frgLog as LogFragment).log("Error de protocolo", false)
                buzzer?.start()
            }

            override fun onResponse(
                call: Call<List<OrdenFabricacionOperacion>>,
                response: Response<List<OrdenFabricacionOperacion>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    consumirOperaciones(serviceOf, response.body()!!, idMaquina)
                } else {
                    (frgLog as LogFragment).log("Error en la respuesta", false)
                    buzzer?.start()
                }
            }

        })

    }

    private fun consumirOperaciones(
        servicio: OrdenFabricacionService,
        operaciones: List<OrdenFabricacionOperacion>,
        idMaquina: Int
    ) {
        var body: BodyConsumirOperacionBarquilla? = null

        var idOrdenes = operaciones.map { it.idOrdenFabricacion }
        var idOrdenesDistinct = operaciones.map { it.idOrdenFabricacion }.distinct()


        if (idOrdenes.size != idOrdenesDistinct.size) {
            if (memoriaConsumo == null || (memoriaConsumo != null && memoriaConsumo?.idsOrdenes!!.isNotEmpty() && !idOrdenes.any {
                    memoriaConsumo?.idsOrdenes!!.contains(
                        it
                    )
                })) {
                memoriaConsumo = null
                val descripciones = operaciones.map { it.descripcion }.distinct()
                buzzerMultioperacion?.start()
                Dialogos.mostrarDialogoMultiOperacion(
                    descripciones,
                    operaciones,
                    servicio,
                    idMaquina,
                    ::consumirOperacionesMulti,
                    ctx!!
                )
            } else {
                consumirOperacionesMulti(
                    servicio,
                    operaciones,
                    idMaquina,
                    memoriaConsumo?.descripciones!!
                )
            }

        } else {
            for (ofo in operaciones) {
                consumirOperacionBarquilla(
                    servicio,
                    BodyConsumirOperacionBarquilla(ofo, Store.ID_OPERARIO, idMaquina)
                )
            }
        }
    }

    private fun consumirOperacionesMulti(
        servicio: OrdenFabricacionService,
        operaciones: List<OrdenFabricacionOperacion>,
        idMaquina: Int,
        descripciones: ArrayList<String>
    ) {
        var body: BodyConsumirOperacionBarquilla? = null

        var idOrdenes = operaciones.map { it.idOrdenFabricacion }
        var idOrdenesDistinct = operaciones.map { it.idOrdenFabricacion }.distinct()


        if (idOrdenes.size != idOrdenesDistinct.size && descripciones.isNotEmpty()) {
            memoriaConsumo = MemoriaConsumo(ArrayList(idOrdenesDistinct),descripciones)
            (frgLog as LogFragment).log("Elección memorizada - limpiar para olvidar", true)

            for (ofo in operaciones) {
                if (descripciones.contains(ofo.descripcion)) {
                    consumirOperacionBarquilla(
                        servicio,
                        BodyConsumirOperacionBarquilla(ofo, Store.ID_OPERARIO, idMaquina)
                    )
                }
            }
        }

    }

    private fun consumirOperacionBarquilla(
        servicio: OrdenFabricacionService,
        body: BodyConsumirOperacionBarquilla
    ) {
        val callConsumo = servicio.consumirBarquillaOperacion(body!!)
        callConsumo.enqueue(object : Callback<List<Consumo>> {
            override fun onFailure(call: Call<List<Consumo>>, t: Throwable) {
                (frgLog as LogFragment).log("Error en la petición", false)
                buzzer?.start()
            }

            override fun onResponse(call: Call<List<Consumo>>, response: Response<List<Consumo>>) {
                if (response.isSuccessful && response.body() != null)
                    consumosRecibidos(body.idMaquina, response.body()!!)
            }

        })
    }

    /**
     * Consume una etiqueta en la máquina que esté seleccionada actualmente
     */
    private fun consumirEtiqueta(msg: String) {
        if (this.maquina == null) {
            Dialogos.mostrarDialogoInformacion("Primero selecciona una máquina", this)
            buzzer?.start()
            return
        }
        val serviceTarea = TareaProgramadaService()
        val serviceOf = OrdenFabricacionService()

        val callTarea = serviceTarea.consumirEnMaquina(
            MaquinaEtiqueta(
                this.maquina?.codigoEtiqueta,
                msg,
                0,
                Store.ID_OPERARIO
            )
        )
        val callOperaciones =
            serviceOf.buscarOperacionesPorPrepaqueteMaquina(msg, this.maquina?.codigoEtiqueta!!)

        val idMaquina = this.maquina!!.id

        callOperaciones.enqueue(object : Callback<List<OrdenFabricacionOperacion>> {
            override fun onFailure(call: Call<List<OrdenFabricacionOperacion>>, t: Throwable) {
                (frgLog as LogFragment).log("Error en la petición", false)
                buzzer?.start()
            }

            override fun onResponse(
                call: Call<List<OrdenFabricacionOperacion>>,
                response: Response<List<OrdenFabricacionOperacion>>
            ) {
                if (response.body() != null) {
                    when {
                        response.body()!!.size == 1 -> callTarea.enqueue(object :
                            Callback<List<Consumo>> {
                            override fun onFailure(call: Call<List<Consumo>>, t: Throwable) {
                                (frgLog as LogFragment).log("Error en la petición", false)
                                buzzer?.start()
                            }

                            override fun onResponse(
                                call: Call<List<Consumo>>,
                                response: Response<List<Consumo>>
                            ) {
                                if (response.body() != null) {
                                    consumosRecibidos(idMaquina, response.body()!!)
                                } else {
                                    buzzer?.start()
                                    (frgLog as LogFragment).log("Respuesta == null", false)
                                }
                            }

                        })
                        response.body()!!.size > 1 -> {
                            buzzerMultioperacion?.start()
                            Dialogos.mostrarDialogoMultiOperacion(
                                response.body()!!,
                                ::consumirOperacion,
                                msg,
                                ctx!!
                            )
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

    private fun consumirOperacion(
        operacion: OrdenFabricacionOperacion,
        codigoEtiqueta: String
    ): Unit {

        val serviceTarea = TareaProgramadaService()
        val callTarea = serviceTarea.consumirEnMaquina(
            MaquinaEtiqueta(
                this.maquina?.codigoEtiqueta,
                codigoEtiqueta,
                operacion.id,
                Store.ID_OPERARIO
            )
        )
        val idMaquina = this.maquina!!.id

        callTarea.enqueue(object : Callback<List<Consumo>> {
            override fun onFailure(call: Call<List<Consumo>>, t: Throwable) {
                (frgLog as LogFragment).log("Error en la petición", false)
                buzzer?.start()
            }

            override fun onResponse(call: Call<List<Consumo>>, response: Response<List<Consumo>>) {
                if (response.body() != null) {
                    consumosRecibidos(idMaquina, response.body()!!)
                } else {
                    buzzer?.start()
                    Dialogos.mostrarDialogoInformacion("Respuesta == NULl", ctx!!)
                }
            }

        })
    }

    /**
     * busca una máquina mediante código de barras
     * si la encuentra la mete en la lista
     */
    private fun findMaquina(cod: String) {
        val service = MaquinaService()
        val call = service.findMaquinaByCodigoEtiqueta(cod)
        call.enqueue(object : Callback<Maquina> {
            override fun onFailure(call: Call<Maquina>, t: Throwable) {
                Dialogos.mostrarDialogoInformacion(t.message ?: "Error en la petición", ctx!!)
            }

            override fun onResponse(call: Call<Maquina>, response: Response<Maquina>) {
                if (response.body() != null) {
                    seleccionarMaquina(response.body()!!)
                } else {
                    log!!.log("RESPUESTA == NIL", false)
                }
            }

        })
    }

    /**
     * Añade a la maquina indicada lso consumos realizados
     * Si hay algun consumo que acaba de terminar alguna tarea (talla)
     * lo comunica con un buzzer y un mensaje en la barra de notificacion inferior
     */
    private fun consumosRecibidos(idMaquina: Int, consumos: List<Consumo>) {
        if (consumos == null || consumos.isEmpty()) {
            buzzer?.start()
            log!!.log("Lectura repetida", false)
            return
        }

        var notificartarea = sharedPreferences?.getBoolean(NOTIFICAR_TAREA, true)!!
        var notificarnota = sharedPreferences?.getBoolean(NOTIFICAR_NOTA, true)!!

        val maq = maquinas.firstOrNull { x -> x.id == idMaquina }
        var consumosTareasAcabadas = ArrayList<Consumo>()
        var consumosNotasAcabadas = ArrayList<Consumo>()

        for (consumo in consumos) {
            MqttCliente.consumirTarea(consumo)
            if (consumo.estado == 0 || consumo.estado == 1) {
                consumosTareasAcabadas.add(consumo)
            } else if (consumo.estado == 3) {
                consumosNotasAcabadas.add(consumo)
            }
        }

        if (consumosNotasAcabadas.size > 0) {
            buzzerNotaAcabada?.start()
            if (notificarnota) {
                var notas = ""
                for (c in consumosNotasAcabadas) {
                    notas += c.codigoOrden + ", "
                }
                Dialogos.mostrarDialogoInformacion("Has acabado las notas: $notas", ctx!!)
            }
        } else if (consumosTareasAcabadas.size > 0) {
            buzzerTareaAcabada?.start()
            if (notificartarea) {
                Dialogos.mostrarDialogoTareasAcabadas(this, consumosTareasAcabadas)
            }
        }

        log!!.log("OK", true)


        for (consumo in consumos) {
            if (consumo.cantidadPendienteAnterior != consumo.cantidadPendiente) {
                maq?.consumos?.add(consumo)
            }
        }
        adapter?.notifyDataSetChanged()
    }

    /**
     * Lleva a la posicion superior la maquina seleccionada
     * y le cambia el estado a isSeleccionada = true
     */
    private fun seleccionarMaquina(maq: Maquina) {
        var maquinaExistente = this.maquinas.firstOrNull { x -> x.id.equals(maq.id) }

        if (maquinaExistente != null) {
            this.maquinas.remove(maquinaExistente)
            this.maquina = maquinaExistente
        } else {
            this.maquina = maq
        }

        for (m in this.maquinas) {
            m.isSeleccionada = false
        }

        this.maquina?.isSeleccionada = true
        this.maquinas.add(0, this.maquina!!)


        this.adapter?.notifyDataSetChanged()
    }

    override fun desconsumir(maq: Maquina) {
        for (consumo in maq.consumos) {
            val service = TareaProgramadaService()
            val call =
                service.desconsumirEtiqueta(Desconsumo(consumo.codPrepaquete, consumo.idOperacion))
            call.enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    (frgLog as LogFragment).log("Error en la petición", false)
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        maq.consumos.remove(consumo)
                        if (maq.consumos.isEmpty()) {
                            maquinas.remove(maq)
                            adapter?.notifyDataSetChanged()
                        }

                        if (maquina != null && maquina!!.equals(maq)) {
                            maquina = null
                        }
                    }
                }

            })
        }
    }

    /**
     * finaliza la actividad
     */
    fun atras(v: View) {
        this.finish()
    }

    fun limpiar(v: View) {
        this.maquina = null
        this.maquinas.clear()
        this.adapter?.notifyDataSetChanged()
        this.memoriaConsumo = null
    }
}

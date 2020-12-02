package com.arneplant.logisticainterna_kot2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arneplant.logisticainterna_kot2.adapter.TareaProgramadaAdapter
import com.arneplant.logisticainterna_kot2.delegate.BuscadorFragmentDelegate
import com.arneplant.logisticainterna_kot2.fragment.LogFragment
import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.TareaPendiente
import com.arneplant.logisticainterna_kot2.network_implementation.GestionPaqueteMaquina
import com.arneplant.logisticainterna_kot2.network_implementation.MaquinaService
import com.arneplant.logisticainterna_kot2.network_implementation.TareaProgramadaService
import com.arneplant.logisticainterna_kot2.util.Tipo
import com.arneplant.logisticainterna_kot2.util.Utils
import kotlinx.android.synthetic.main.activity_programacion_maquina.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProgramacionMaquinaActivity : AppCompatActivity(), BuscadorFragmentDelegate {

    private var log: LogFragment? = null
    private var tareas : ArrayList<TareaPendiente> = ArrayList()
    private var adapter: TareaProgramadaAdapter?  = null
    private var maquina: Maquina? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programacion_maquina)
        this.adapter = TareaProgramadaAdapter(this,this.tareas)
        this.lista.adapter = this.adapter
        this.title = "Programación Máquina"
        this.log = frgLog as LogFragment
    }

    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        when (Utils.getTipo(msg)) {
            Tipo.Maquina -> {
                findMaquina(msg) // carga - descarga
            }

        }
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

        val service = TareaProgramadaService()
        val call = service.listarTareasMaquina(cod)
        call.enqueue(object: Callback<List<TareaPendiente>> {
            override fun onFailure(call: Call<List<TareaPendiente>>, t: Throwable) {
                log!!.log(t.message ?: "Error en la petición", false)
                tareas.clear()
                adapter?.notifyDataSetChanged()
            }

            override fun onResponse(
                call: Call<List<TareaPendiente>>,
                response: Response<List<TareaPendiente>>
            ) {
                if(response.body()!=null){
                    tareas.clear()
                    tareas.addAll(response.body()!!)
                    adapter?.notifyDataSetChanged()
                }
            }

        })
    }

    fun atras(v: View){
        this.finish()
    }

}

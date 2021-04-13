package com.arneplant.logisticainterna_kot2

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arneplant.logisticainterna_kot2.adapter.UtillajeFiltroAdapter
import com.arneplant.logisticainterna_kot2.adapter.UtillajeUbicacionAdapter
import com.arneplant.logisticainterna_kot2.delegate.BuscadorFragmentDelegate
import com.arneplant.logisticainterna_kot2.delegate.FiltroUtillajeCambiadoDelegate
import com.arneplant.logisticainterna_kot2.fragment.LogFragment
import com.arneplant.logisticainterna_kot2.model.dto.FiltroUtillaje
import com.arneplant.logisticainterna_kot2.model.dto.UtillajeUbicacion
import com.arneplant.logisticainterna_kot2.model.dto.UtillajesTallasColeccion
import com.arneplant.logisticainterna_kot2.network_implementation.UtillajeService
import com.arneplant.logisticainterna_kot2.util.Tipo
import com.arneplant.logisticainterna_kot2.util.Utils
import kotlinx.android.synthetic.main.activity_buscar_utillajes.*
import kotlinx.android.synthetic.main.activity_ubicar.*
import kotlinx.android.synthetic.main.activity_ubicar.frgLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscarUtillajesActivity : AppCompatActivity(),BuscadorFragmentDelegate {

    var buzzer: MediaPlayer? = null
    var ubicaciones = ArrayList<UtillajeUbicacion>()
    var filtros = ArrayList<FiltroUtillaje>()
    var adapterUbicaciones :UtillajeUbicacionAdapter? = null
    var adapterFiltros : UtillajeFiltroAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_utillajes)
        this.title = "Buscar Utillajes"
        this.buzzer = MediaPlayer.create(this, R.raw.buzzer)

        this.adapterUbicaciones = UtillajeUbicacionAdapter(this,ubicaciones)
        this.adapterFiltros = UtillajeFiltroAdapter(this,filtros)

        this.listaFiltros.adapter = this.adapterFiltros
        this.listaUbicaciones.adapter =this.adapterUbicaciones

        this.adapterFiltros?.setFiltroCambiadoDelegate(object: FiltroUtillajeCambiadoDelegate{
            override fun filtroCambiado() {
                var ubicacionesBorrar = ArrayList<UtillajeUbicacion>()
                for(ub in ubicaciones){
                    var borrar = true
                    for(f in filtros){
                        if(ub.codUtillaje.substring(0,2)==f.prefijo){
                            borrar = false
                        }
                    }
                    if(borrar){
                        ubicacionesBorrar.add(ub)
                    }
                }
                for(ub in ubicacionesBorrar){
                    ubicaciones.remove(ub)
                }
                adapterUbicaciones?.notifyDataSetChanged()
            }
        })
    }

    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        when(Utils.getTipo(msg)){
            Tipo.Barquilla->{
                buscarPorBarquilla(msg)
            }
            Tipo.OF->{

            }

        }
    }

    private fun buscarPorBarquilla(cod: String){
        this.ubicaciones.clear()
        this.filtros.clear()
        adapterFiltros?.notifyDataSetChanged()
        adapterUbicaciones?.notifyDataSetChanged()

        val service = UtillajeService()
        val call = service.buscarUbicacionesPorBarquilla(cod)
        call.enqueue(object: Callback<List<UtillajeUbicacion>>{
            override fun onFailure(call: Call<List<UtillajeUbicacion>>, t: Throwable) {
                (frgLog as LogFragment).log("Error de protocolo",false)
                buzzer?.start()
            }

            override fun onResponse(
                call: Call<List<UtillajeUbicacion>>,
                response: Response<List<UtillajeUbicacion>>
            ) {
                if(response.isSuccessful && response.body()!=null){
                    cargarUbicaciones(response.body()!!)
                }
                else{
                    (frgLog as LogFragment).log("Error de respuesta",false)
                    buzzer?.start()
                }
            }

        })
    }

    private fun cargarUbicaciones(ubs: List<UtillajeUbicacion>){
        this.ubicaciones.clear()
        this.filtros.clear()

        this.ubicaciones.addAll(ubs)
        val prefijos = this.ubicaciones.map { x->x.codUtillaje.substring(0,2) }.distinct()
        for(pre in prefijos){
            filtros.add(FiltroUtillaje(pre))
        }

        adapterFiltros?.notifyDataSetChanged()
        adapterUbicaciones?.notifyDataSetChanged()
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

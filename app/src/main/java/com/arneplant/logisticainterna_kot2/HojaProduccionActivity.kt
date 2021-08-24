package com.arneplant.logisticainterna_kot2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arneplant.logisticainterna_kot2.adapter.LaboresDisponiblesAdapter
import com.arneplant.logisticainterna_kot2.adapter.LaboresOperarioAdapter
import com.arneplant.logisticainterna_kot2.application.Store
import com.arneplant.logisticainterna_kot2.delegate.EliminarLaborOperarioDelegate
import com.arneplant.logisticainterna_kot2.delegate.RegistrarLaborOperarioDelegate
import com.arneplant.logisticainterna_kot2.model.Labor
import com.arneplant.logisticainterna_kot2.model.LaborOperario
import com.arneplant.logisticainterna_kot2.model.dto.EliminarLaborOperario
import com.arneplant.logisticainterna_kot2.model.dto.RegistrarLaborOperario
import com.arneplant.logisticainterna_kot2.model.dto.UbicacionTarea
import com.arneplant.logisticainterna_kot2.network_implementation.OperacionService
import com.arneplant.logisticainterna_kot2.network_implementation.OperarioService
import kotlinx.android.synthetic.main.activity_hoja_produccion.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HojaProduccionActivity : AppCompatActivity() {
    lateinit var mRecyclerViewLaboresDisponibles: RecyclerView
    lateinit var mRecyclerViewLaboresOperario: RecyclerView

    val mAdapterLaboresDisponibles: LaboresDisponiblesAdapter = LaboresDisponiblesAdapter()
    val mAdapterLaboresOperario: LaboresOperarioAdapter = LaboresOperarioAdapter()

    val servicioOperarios = OperarioService()
    val servicioOperacion = OperacionService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoja_produccion)


        traerLabores()
        traerLaboresOperario()
    }

    fun traerLaboresOperario() {
        val call = servicioOperarios.obtenerLaboresTurnoActual(Store.ID_OPERARIO)
        call.enqueue(object : Callback<List<LaborOperario>> {
            override fun onFailure(call: Call<List<LaborOperario>>, t: Throwable) {
                Toast.makeText(MiAplicacion.context,"error",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<LaborOperario>>,
                response: Response<List<LaborOperario>>
            ) {
                if (response.isSuccessful && response.body() != null) {

                    val mutableList: MutableList<LaborOperario> = ArrayList()
                    mutableList.addAll(response.body()!!.sortedByDescending { x -> x.fechaInicioLabor })
                    cargarLaboresOperario(mutableList)
                } else {
                    //errorAlObtenerLabores()
                }
            }

        })


    }

    fun cargarLaboresOperario(lista: MutableList<LaborOperario>) {
        mRecyclerViewLaboresOperario = findViewById(R.id.listaLaboresOperario) as RecyclerView
        mRecyclerViewLaboresOperario.setHasFixedSize(true)
        mRecyclerViewLaboresOperario.layoutManager = LinearLayoutManager(MiAplicacion.context)
        mAdapterLaboresOperario.LaboresOperarioAdapter(lista, MiAplicacion.context,
            object : EliminarLaborOperarioDelegate {
                override fun eliminar(labor: LaborOperario) {
                    val eliminarBody = EliminarLaborOperario()
                    eliminarBody.idOperario = Store.ID_OPERARIO
                    eliminarBody.idOperarioLabor = labor.idLaborOperario
                    val call = servicioOperarios.eliminarLabor(eliminarBody)
                    call.enqueue(object: Callback<List<LaborOperario>>{
                        override fun onFailure(call: Call<List<LaborOperario>>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<List<LaborOperario>>,
                            response: Response<List<LaborOperario>>
                        ) {
                            val mutableList: MutableList<LaborOperario> = ArrayList()
                            mutableList.addAll(response.body()!!.sortedByDescending { x -> x.fechaInicioLabor })
                            cargarLaboresOperario(mutableList)
                        }

                    })
                }
            })
        mRecyclerViewLaboresOperario.adapter = mAdapterLaboresOperario
    }

    fun cargarLaboresDisponibles(lista: MutableList<Labor>) {
        mRecyclerViewLaboresDisponibles = findViewById(R.id.listaLaboresDisponibles) as RecyclerView
        mRecyclerViewLaboresDisponibles.setHasFixedSize(true)
        mRecyclerViewLaboresDisponibles.layoutManager = GridLayoutManager(MiAplicacion.context, 3)

        mAdapterLaboresDisponibles.LaboresDisponiblesAdapter(lista, MiAplicacion.context,
            object : RegistrarLaborOperarioDelegate {
                override fun registrar(labor: Labor) {
                    val registrarBody = RegistrarLaborOperario()
                    registrarBody.idLabor = labor.id
                    registrarBody.idOperario = Store.ID_OPERARIO
                    val call = servicioOperarios.registrarLabor(registrarBody)
                    call.enqueue(object: Callback<List<LaborOperario>>{
                        override fun onFailure(call: Call<List<LaborOperario>>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<List<LaborOperario>>,
                            response: Response<List<LaborOperario>>
                        ) {
                            if(response.isSuccessful && response.body()!= null){
                                val mutableList: MutableList<LaborOperario> = ArrayList()
                                mutableList.addAll(response.body()!!.sortedByDescending { x -> x.fechaInicioLabor })
                                cargarLaboresOperario(mutableList)
                            }
                        }

                    })
                }
            })
        mRecyclerViewLaboresDisponibles.adapter = mAdapterLaboresDisponibles
    }

    fun traerLabores() {
        val call = servicioOperacion.findLabores()
        call.enqueue(object : Callback<List<Labor>> {
            override fun onFailure(call: Call<List<Labor>>, t: Throwable) {
                errorAlObtenerLabores()
            }

            override fun onResponse(call: Call<List<Labor>>, response: Response<List<Labor>>) {
                if (response.isSuccessful && response.body() != null) {
                    val mutableList: MutableList<Labor> = ArrayList()
                    mutableList.addAll(response.body()!!.sortedBy { x -> x.descripcion })
                    cargarLaboresDisponibles(mutableList)
                } else {
                    errorAlObtenerLabores()
                }
            }

        })
    }

    fun errorAlObtenerLabores() {
        val mutableList: MutableList<Labor> = ArrayList()
        cargarLaboresDisponibles(mutableList)
    }


}

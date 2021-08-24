package com.arneplant.logisticainterna_kot2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arneplant.logisticainterna_kot2.adapter.LaboresDisponiblesAdapter
import com.arneplant.logisticainterna_kot2.model.Labor
import com.arneplant.logisticainterna_kot2.model.dto.UbicacionTarea
import com.arneplant.logisticainterna_kot2.network_implementation.OperacionService
import kotlinx.android.synthetic.main.activity_hoja_produccion.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HojaProduccionActivity : AppCompatActivity() {
    lateinit var mRecyclerView : RecyclerView
    val mAdapter : LaboresDisponiblesAdapter = LaboresDisponiblesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoja_produccion)

        val servicioOperacion = OperacionService()
        val call = servicioOperacion.findLabores()
        call.enqueue(object : Callback<List<Labor>> {
            override fun onFailure(call: Call<List<Labor>>, t: Throwable) {
                errorAlObtenerLabores()
            }

            override fun onResponse(call: Call<List<Labor>>, response: Response<List<Labor>>) {
                if (response.isSuccessful && response.body() != null) {
                    mRecyclerView = findViewById(R.id.listaLaboresDisponibles) as RecyclerView
                    mRecyclerView.setHasFixedSize(true)
                    mRecyclerView.layoutManager = GridLayoutManager(MiAplicacion.context,3)
                    val mutableList : MutableList<Labor> = ArrayList()
                    mutableList.addAll(response.body()!!.sortedBy { x->x.descripcion })
                    mAdapter.LaboresDisponiblesAdapter(mutableList, MiAplicacion.context)
                    mRecyclerView.adapter = mAdapter
                } else {
                    errorAlObtenerLabores()
                }
            }

        })
    }


    fun errorAlObtenerLabores() {
        mRecyclerView = findViewById(R.id.listaLaboresDisponibles) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = GridLayoutManager(this,2)
        val mutableList : MutableList<Labor> = ArrayList()
        mAdapter.LaboresDisponiblesAdapter(mutableList, this)
        mRecyclerView.adapter = mAdapter
    }



}

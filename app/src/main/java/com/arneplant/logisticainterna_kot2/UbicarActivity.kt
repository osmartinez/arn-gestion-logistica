package com.arneplant.logisticainterna_kot2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arneplant.logisticainterna_kot2.adapter.UbicacionAdapter
import com.arneplant.logisticainterna_kot2.model.UbicacionPaquetes
import kotlinx.android.synthetic.main.activity_ubicar.*

class UbicarActivity : AppCompatActivity() {

    var ubicacionesPaquetes: ArrayList<UbicacionPaquetes> = ArrayList()
    var adapter: UbicacionAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicar)
        this.title = "Ubicar"
        // test
        this.ubicacionesPaquetes.add(UbicacionPaquetes("UBICACION 1", 2))
        this.ubicacionesPaquetes.add(UbicacionPaquetes("UBICACION 2", 3))
        //
        this.adapter = UbicacionAdapter(this,this.ubicacionesPaquetes)
        this.lista.adapter =this.adapter
    }

    fun atras(v:View) {
        this.finish()
    }

    fun limpiar(v:View) {
        this.ubicacionesPaquetes.clear()
        this.adapter?.notifyDataSetChanged()
    }

}

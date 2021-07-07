package com.arneplant.logisticainterna_kot2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arneplant.logisticainterna_kot2.network.MqttCliente
import kotlinx.android.synthetic.main.tab1_fragment.view.*

class PrimerTabFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tab1_fragment, container, false)

        view.btLocalizarTarea.setOnClickListener { localizarTarea() }
        view.btDejarMAquina.setOnClickListener { dejarEnMaquina() }
        view.btSacarMaquina.setOnClickListener { sacarDeMaquina() }
        view.btUbicar.setOnClickListener { ubicar() }
        view.btEditarEtiqueta.setOnClickListener { editarEtiqueta() }
        view.btSalir.setOnClickListener { salir() }

        return view
    }

    fun dejarEnMaquina(){
        val intent = Intent(activity, DejarEnMaquinaActivity::class.java)
        startActivity(intent)

    }

    fun sacarDeMaquina(){
        val intent = Intent(activity, SacarDeMaquinaActivity::class.java)
        startActivity(intent)
    }

    fun ubicar(){
        val intent = Intent(activity, UbicarActivity::class.java)
        startActivity(intent)
    }

    fun editarEtiqueta(){
        val intent = Intent(activity, ModificarPrepaqueteActivity::class.java)
        startActivity(intent)
    }

    private fun localizarTarea() {
        val intent = Intent(activity, LocalizarTareaActivity::class.java)
        startActivity(intent)    }

    fun salir(){

        var sharedPref = activity!!.getSharedPreferences("MEMORIA_INTERNA", Context.MODE_PRIVATE)?:return
        with(sharedPref.edit()){
            putString("OPERARIO_CODIGO",null)
            putString("OPERARIO_NOMBRE",null)
            putInt("OPERARIO_ID",0)

            commit()
        }

        MqttCliente.cerrar()

        for(frag in activity!!.supportFragmentManager.fragments){
            activity!!.supportFragmentManager.beginTransaction().remove(frag).commit()
        }
        activity!!.supportFragmentManager
            .beginTransaction()
            .add(R.id.container, LoginFragment())
            .commit()
    }
}
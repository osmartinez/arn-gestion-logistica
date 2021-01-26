package com.arneplant.logisticainterna_kot2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arneplant.logisticainterna_kot2.network.MqttCliente

class PrimerTabFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun dejarEnMaquina(v: View){
        val intent = Intent(activity, DejarEnMaquinaActivity::class.java)
        startActivity(intent)

    }

    fun sacarDeMaquina(v: View){
        val intent = Intent(activity, SacarDeMaquinaActivity::class.java)
        startActivity(intent)
    }

    fun ubicar(v:View){
        val intent = Intent(activity, UbicarActivity::class.java)
        startActivity(intent)
    }

    fun verProgramacion(v:View){
        val intent = Intent(activity, ProgramacionMaquinaActivity::class.java)
        startActivity(intent)
    }

    fun editarEtiqueta(v:View){
        val intent = Intent(activity, ModificarPrepaqueteActivity::class.java)
        startActivity(intent)
    }

    fun salir(v:View){

        var sharedPref = activity!!.getSharedPreferences("MEMORIA_INTERNA", Context.MODE_PRIVATE)?:return
        with(sharedPref.edit()){
            putString("OPERARIO_CODIGO",null)
            putString("OPERARIO_NOMBRE",null)
            putInt("OPERARIO_ID",0)

            commit()
        }

        MqttCliente.cerrar()

        activity!!.supportFragmentManager
            .beginTransaction()
            .add(R.id.container, LoginFragment())
            .commit()
    }
}
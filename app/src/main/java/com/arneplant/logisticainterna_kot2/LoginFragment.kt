package com.arneplant.logisticainterna_kot2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arneplant.logisticainterna_kot2.application.Store
import com.arneplant.logisticainterna_kot2.model.Operario
import com.arneplant.logisticainterna_kot2.network_implementation.OperarioService
import com.arneplant.logisticainterna_kot2.util.Dialogos
import kotlinx.android.synthetic.main.login_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    var ctx: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        val sharedPref = activity?.getSharedPreferences("MEMORIA_INTERNA",Context.MODE_PRIVATE) ?: return view
        val defaultValue = null
        val codigoOperario = sharedPref.getString("OPERARIO_CODIGO", defaultValue)
        val nombreOperario = sharedPref.getString("OPERARIO_NOMBRE", defaultValue)
        val idOperario = sharedPref.getInt("OPERARIO_ID", 0)

        if(codigoOperario != null && nombreOperario!=null){
            guardarStore(nombreOperario,codigoOperario,idOperario)
            (activity as NavigationHost).navigateTo(PrincipalFragment(), false)

        }

        return view
    }


    fun guardarStore(nombre: String,codigo: String,id:Int){
        Store.CODIGO_OPERARIO = codigo
        Store.NOMBRE_OPERARIO = nombre
        Store.ID_OPERARIO = id
    }

    fun  clickNumero(v: View){
        var btn: info.hoang8f.widget.FButton = v as info.hoang8f.widget.FButton
        var numero = btn.text
        tbCodigoOperario.setText(tbCodigoOperario.text.toString()+numero)
    }

    fun borrar(v:View){
        tbCodigoOperario.setText(tbCodigoOperario.text.toString().dropLast(1))
    }

    fun ok(v:View){
        val service = OperarioService()
        val call = service.buscarPorCodigo("B00"+tbCodigoOperario.text.toString())
        call.enqueue(object: Callback<Operario>{
            override fun onFailure(call: Call<Operario>, t: Throwable) {
                Dialogos.mostrarDialogoInformacion("Error petición", ctx!!)
            }

            override fun onResponse(call: Call<Operario>, response: Response<Operario>) {
                var error: Boolean = false
                if(response.isSuccessful){
                    var operario = response.body()!!
                    if(operario.nombre!=null && operario.nombre != ""){
                        var sharedPref = ctx?.getSharedPreferences("MEMORIA_INTERNA", Context.MODE_PRIVATE)?:return
                        with(sharedPref.edit()){
                            putString("OPERARIO_CODIGO","${operario.codigoObrero}")
                            putString("OPERARIO_NOMBRE","${operario.nombre}")
                            putInt("OPERARIO_ID",operario.id)
                            commit()
                        }

                        guardarStore(operario.nombre,operario.codigoObrero,operario.id)

                    }
                    else{
                        error = true
                    }
                }
                else {
                    error = true
                }

                if(error){
                    Dialogos.mostrarDialogoInformacion("El operario ${tbCodigoOperario.text} no existe", ctx!!)
                }
                else{
                    (activity as NavigationHost).navigateTo(PrincipalFragment(), false)
                }
            }

        })
    }

}

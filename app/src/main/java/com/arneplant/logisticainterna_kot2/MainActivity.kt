package com.arneplant.logisticainterna_kot2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arneplant.logisticainterna_kot2.network.MqttClientHelper
import com.arneplant.logisticainterna_kot2.network.MqttCliente
import com.arneplant.logisticainterna_kot2.util.ApkUpdateAsyncTask

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val app_name = packageManager.getPackageInfo(packageName, 0).packageName
        val app_version = packageManager.getPackageInfo(packageName,0).versionCode
        this.title = "Logística Interna v${app_version}"

        var url_apk = "http://192.168.0.104/aplicaciones/$app_name/app-debug.apk"
        var url_v = "http://192.168.0.104/aplicaciones/$app_name/version.json"

        var atualizaApp = ApkUpdateAsyncTask()
        atualizaApp.setContext(applicationContext)
        atualizaApp.execute(url_apk,url_v)

        val sharedPref = this.getSharedPreferences("MEMORIA_INTERNA",Context.MODE_PRIVATE) ?: return
        val defaultValue = null
        val codigoOperario = sharedPref.getString("OPERARIO_CODIGO", defaultValue)
        val nombreOperario = sharedPref.getString("OPERARIO_NOMBRE", defaultValue)

        if(codigoOperario != null && nombreOperario!=null){
            this.title = "${codigoOperario} ${nombreOperario}"
            MqttCliente.iniciar(this,codigoOperario)
        }
        else{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
        }


    }

    fun dejarEnMaquina(v: View){
        val intent = Intent(this, DejarEnMaquinaActivity::class.java)
        startActivity(intent)
    }

    fun sacarDeMaquina(v: View){
        val intent = Intent(this, SacarDeMaquinaActivity::class.java)
        startActivity(intent)
    }

    fun ubicar(v:View){
        val intent = Intent(this, UbicarActivity::class.java)
        startActivity(intent)
    }

    fun verProgramacion(v:View){
        val intent = Intent(this, ProgramacionMaquinaActivity::class.java)
        startActivity(intent)
    }

    fun editarEtiqueta(v:View){
        val intent = Intent(this, ModificarPrepaqueteActivity::class.java)
        startActivity(intent)
    }

    fun salir(v:View){

        var sharedPref = this.getSharedPreferences("MEMORIA_INTERNA", Context.MODE_PRIVATE)?:return
        with(sharedPref.edit()){
            putString("OPERARIO_CODIGO",null)
            putString("OPERARIO_NOMBRE",null)
            putString("OPERARIO_ID",null)

            commit()
        }

        MqttCliente.cerrar()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}

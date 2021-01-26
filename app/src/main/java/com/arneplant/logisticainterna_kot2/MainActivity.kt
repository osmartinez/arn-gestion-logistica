package com.arneplant.logisticainterna_kot2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arneplant.logisticainterna_kot2.application.Store
import com.arneplant.logisticainterna_kot2.network.MqttCliente
import com.arneplant.logisticainterna_kot2.util.ApkUpdateAsyncTask

class MainActivity : AppCompatActivity(), NavigationHost {
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


        if(savedInstanceState == null ||Store.ID_OPERARIO!=0){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, LoginFragment())
                .commit()
        }
    }

    override fun navigateTo(fragment: Fragment, addToBackstack: Boolean) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)

        if (addToBackstack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }



}

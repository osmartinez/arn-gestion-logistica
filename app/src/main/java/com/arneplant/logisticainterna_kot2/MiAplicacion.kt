package com.arneplant.logisticainterna_kot2

import android.app.Application
import android.content.Context

class MiAplicacion: Application(){

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: MiAplicacion
            private set
    }

}
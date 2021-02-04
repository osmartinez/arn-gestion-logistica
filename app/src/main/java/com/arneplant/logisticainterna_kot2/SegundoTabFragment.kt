package com.arneplant.logisticainterna_kot2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arneplant.logisticainterna_kot2.application.Store
import com.arneplant.logisticainterna_kot2.network.MqttCliente
import kotlinx.android.synthetic.main.tab2_fragment.view.*

class SegundoTabFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tab2_fragment, container, false)

        view.btImprimirHojaProduccion.setOnClickListener { MqttCliente.imprimirHojaProduccion(Store.ID_OPERARIO) }

        return view
    }
}
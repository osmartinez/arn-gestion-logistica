package com.arneplant.logisticainterna_kot2.network_implementation

import com.arneplant.logisticainterna_kot2.model.TareaPendiente
import com.arneplant.logisticainterna_kot2.model.dto.Consumo
import com.arneplant.logisticainterna_kot2.model.dto.Desconsumo
import com.arneplant.logisticainterna_kot2.model.dto.MaquinaEtiqueta
import com.arneplant.logisticainterna_kot2.network.RetrofitInstance
import com.arneplant.logisticainterna_kot2.network_ifaces.IMaquinaService
import com.arneplant.logisticainterna_kot2.network_ifaces.ITareaProgramadaService
import retrofit2.Call

class TareaProgramadaService: ITareaProgramadaService {
    override fun desconsumirEtiqueta(desconsumo: Desconsumo): Call<Void> {
        return service.desconsumirEtiqueta(desconsumo)
    }

    private var service: ITareaProgramadaService = RetrofitInstance.getRetrofitInstance()!!.create(
        ITareaProgramadaService::class.java)

    override fun listarTareasMaquina(codMaquina: String): Call<List<TareaPendiente>> {
        return service.listarTareasMaquina(codMaquina)
    }

    override fun programarTareaMaquina(
        maquinaEtiqueta: MaquinaEtiqueta
    ): Call<Void> {
        return service.programarTareaMaquina(maquinaEtiqueta)
    }

    override fun consumirEnMaquina(maquinaEtiqueta: MaquinaEtiqueta): Call<List<Consumo>> {
        return service.consumirEnMaquina(maquinaEtiqueta)
    }
    override fun desconsumirEnMaquina(maquinaEtiqueta: MaquinaEtiqueta): Call<Void> {
        return service.desconsumirEnMaquina(maquinaEtiqueta)
    }



}
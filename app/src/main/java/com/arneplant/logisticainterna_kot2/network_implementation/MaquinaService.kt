package com.arneplant.logisticainterna_kot2.network_implementation

import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.network.RetrofitInstance
import com.arneplant.logisticainterna_kot2.network_ifaces.IMaquinaService
import retrofit2.Call

class MaquinaService:IMaquinaService {
    private var service: IMaquinaService = RetrofitInstance.getRetrofitInstance()!!.create(
        IMaquinaService::class.java)

    override fun findMaquinaByCodigoEtiqueta(codigo: String): Call<Maquina> {
        return service.findMaquinaByCodigoEtiqueta(codigo)
    }
}
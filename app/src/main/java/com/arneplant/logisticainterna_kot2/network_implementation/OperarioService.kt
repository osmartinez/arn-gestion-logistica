package com.arneplant.logisticainterna_kot2.network_implementation

import com.arneplant.logisticainterna_kot2.model.Operario
import com.arneplant.logisticainterna_kot2.network.RetrofitInstance
import com.arneplant.logisticainterna_kot2.network_ifaces.IOperarioService
import retrofit2.Call

class OperarioService: IOperarioService {
    private var service: IOperarioService = RetrofitInstance.getRetrofitInstance()!!.create(
        IOperarioService::class.java)

    override fun buscarPorCodigo(codigo: String): Call<Operario> {
        return service.buscarPorCodigo(codigo)
    }
}
package com.arneplant.logisticainterna_kot2.network_implementation

import com.arneplant.logisticainterna_kot2.model.LaborOperario
import com.arneplant.logisticainterna_kot2.model.Operario
import com.arneplant.logisticainterna_kot2.model.dto.EliminarLaborOperario
import com.arneplant.logisticainterna_kot2.model.dto.RegistrarLaborOperario
import com.arneplant.logisticainterna_kot2.network.RetrofitInstance
import com.arneplant.logisticainterna_kot2.network_ifaces.IOperarioService
import retrofit2.Call

class OperarioService: IOperarioService {
    override fun obtenerLaboresTurnoActual(idOperario: Int): Call<List<LaborOperario>> {
        return service.obtenerLaboresTurnoActual(idOperario)
    }

    override fun registrarLabor(registrarBody: RegistrarLaborOperario): Call<List<LaborOperario>> {
        return service.registrarLabor(registrarBody)
    }

    override fun eliminarLabor(eliminarBody: EliminarLaborOperario): Call<List<LaborOperario>> {
        return service.eliminarLabor(eliminarBody)
    }

    private var service: IOperarioService = RetrofitInstance.getRetrofitInstance()!!.create(
        IOperarioService::class.java)

    override fun buscarPorCodigo(codigo: String): Call<Operario> {
        return service.buscarPorCodigo(codigo)
    }
}
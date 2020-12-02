package com.arneplant.logisticainterna_kot2.network_implementation

import com.arneplant.logisticainterna_kot2.model.OrdenFabricacionOperacion
import com.arneplant.logisticainterna_kot2.model.Ubicacion
import com.arneplant.logisticainterna_kot2.network.RetrofitInstance
import com.arneplant.logisticainterna_kot2.network_ifaces.IOrdenFabricacionService
import com.arneplant.logisticainterna_kot2.network_ifaces.IUbicacionService
import retrofit2.Call

class OrdenFabricacionService: IOrdenFabricacionService {
    override fun buscarOperacionesPorPrepaqueteMaquina(
        codigoEtiqueta: String,
        codigoMaquina: String
    ): Call<List<OrdenFabricacionOperacion>> {
        return service.buscarOperacionesPorPrepaqueteMaquina(codigoEtiqueta,codigoMaquina)
    }

    private var service: IOrdenFabricacionService = RetrofitInstance.getRetrofitInstance()!!.create(
        IOrdenFabricacionService::class.java)




}
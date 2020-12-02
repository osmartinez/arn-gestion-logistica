package com.arneplant.logisticainterna_kot2.network_implementation

import com.arneplant.logisticainterna_kot2.model.dto.PrepaqueteInfo
import com.arneplant.logisticainterna_kot2.model.dto.PrepaqueteSeccionDTO
import com.arneplant.logisticainterna_kot2.network.RetrofitInstance
import com.arneplant.logisticainterna_kot2.network_ifaces.IMaquinaService
import com.arneplant.logisticainterna_kot2.network_ifaces.IPrepaqueteService
import retrofit2.Call

class PrepaqueteService:IPrepaqueteService {


    private var service: IPrepaqueteService = RetrofitInstance.getRetrofitInstance()!!.create(
        IPrepaqueteService::class.java)

    override fun buscarPorCodigo(codigoEtiqueta: String): Call<List<PrepaqueteInfo>> {
        return service.buscarPorCodigo(codigoEtiqueta)
    }

    override fun actualizarCantidad(prepaquete: PrepaqueteInfo): Call<List<PrepaqueteInfo>> {
        return service.actualizarCantidad(prepaquete)
    }

    override fun buscarEnSeccion(
        codigoEtiqueta: String,
        codigoSeccion: String
    ): Call<List<PrepaqueteSeccionDTO>> {
        return service.buscarEnSeccion(codigoEtiqueta,codigoSeccion)
    }
}
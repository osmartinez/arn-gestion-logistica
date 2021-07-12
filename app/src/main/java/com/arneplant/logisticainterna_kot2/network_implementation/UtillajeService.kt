package com.arneplant.logisticainterna_kot2.network_implementation

import com.arneplant.logisticainterna_kot2.model.dto.AltaEjemplarDTO
import com.arneplant.logisticainterna_kot2.model.dto.UtillajeUbicacion
import com.arneplant.logisticainterna_kot2.model.dto.UtillajesTallasColeccion
import com.arneplant.logisticainterna_kot2.network.RetrofitInstance
import com.arneplant.logisticainterna_kot2.network_ifaces.IUtillajeService
import retrofit2.Call

class UtillajeService:IUtillajeService {
    override fun altaEjemplar(dto: AltaEjemplarDTO): Call<UtillajesTallasColeccion> {
        return service.altaEjemplar(dto)
    }

    private var service: IUtillajeService = RetrofitInstance.getRetrofitInstance()!!.create(
        IUtillajeService::class.java)

    override fun buscarUbicacionesPorBarquilla(codigoEtiqueta: String): Call<List<UtillajeUbicacion>> {
        return service.buscarUbicacionesPorBarquilla(codigoEtiqueta)
    }

    override fun buscarUbicacionesPorOperacion(idOperacion: Int): Call<List<UtillajeUbicacion>> {
        return service.buscarUbicacionesPorOperacion(idOperacion)
    }

    override fun buscarUbicacionesPorCodigoUtillaje(codUtillaje: String): Call<List<UtillajeUbicacion>> {
        return service.buscarUbicacionesPorCodigoUtillaje(codUtillaje)
    }

    override fun ubicarPorEtiqueta(utillajeUbicacion: UtillajeUbicacion): Call<UtillajesTallasColeccion> {
        return service.ubicarPorEtiqueta(utillajeUbicacion)
    }
}
package com.arneplant.logisticainterna_kot2.network_implementation

import com.arneplant.logisticainterna_kot2.model.Ubicacion
import com.arneplant.logisticainterna_kot2.model.dto.BodyUbicar
import com.arneplant.logisticainterna_kot2.network.RetrofitInstance
import com.arneplant.logisticainterna_kot2.network_ifaces.IUbicacionService
import retrofit2.Call

class UbicacionService:IUbicacionService {
    override fun ubicarBarquilla(body: BodyUbicar): Call<Void> {
        return service.ubicarBarquilla(body)
    }

    private var service: IUbicacionService = RetrofitInstance.getRetrofitInstance()!!.create(IUbicacionService::class.java)

    override fun getUbicacion(id: String): Call<Ubicacion> {
        return service.getUbicacion(id)
    }

    override fun ubicarPrepaquete(idPrepaquete: String, idUbicacion: String): Call<Int> {
        return service.ubicarPrepaquete(idPrepaquete, idUbicacion)
    }


}
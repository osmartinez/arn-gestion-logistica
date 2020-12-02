package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.Ubicacion
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IUbicacionService {
    @GET("api/ubicaciones/{codigoUbicacion}")
    abstract fun getUbicacion(@Path("codigoUbicacion") codigoUbicacion: String): Call<Ubicacion>

    @GET("api/ubicaciones/ubicarPrepaquete/{codigoPrepaquete}/{codigoUbicacion}")
    abstract fun ubicarPrepaquete(@Path("codigoPrepaquete") codigoPrepaquete: String,
                                  @Path("codigoUbicacion") codigoUbicacion: String): Call<Int>
}
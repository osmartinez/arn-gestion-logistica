package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.dto.AltaEjemplarDTO
import com.arneplant.logisticainterna_kot2.model.dto.PrepaqueteSeccionDTO
import com.arneplant.logisticainterna_kot2.model.dto.UtillajeUbicacion
import com.arneplant.logisticainterna_kot2.model.dto.UtillajesTallasColeccion
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IUtillajeService {

    @GET("api/utillajes/buscarUbicaciones/porBarquilla/{codigoEtiqueta}")
    fun buscarUbicacionesPorBarquilla(@Path ("codigoEtiqueta") codigoEtiqueta: String): Call<List<UtillajeUbicacion>>
    @GET("api/utillajes/buscarUbicaciones/porOperacion/{idOperacion}")
    fun buscarUbicacionesPorOperacion(@Path ("idOperacion") idOperacion: Int): Call<List<UtillajeUbicacion>>
    @GET("api/utillajes/buscarUbicaciones/porCodigoUtillaje/{codUtillaje}")
    fun buscarUbicacionesPorCodigoUtillaje(@Path ("codUtillaje") codUtillaje: String): Call<List<UtillajeUbicacion>>
    @POST("api/utillajes/ubicar/porEtiqueta")
    fun ubicarPorEtiqueta(@Body utillajeUbicacion: UtillajeUbicacion): Call<UtillajesTallasColeccion>

    @POST("api/utillajes/alta/altaEjemplar")
    fun altaEjemplar(@Body dto: AltaEjemplarDTO): Call<UtillajesTallasColeccion>
}
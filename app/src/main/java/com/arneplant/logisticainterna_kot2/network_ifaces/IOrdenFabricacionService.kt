package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.OrdenFabricacionOperacion
import com.arneplant.logisticainterna_kot2.model.dto.BodyConsumirOperacionBarquilla
import com.arneplant.logisticainterna_kot2.model.dto.Consumo
import com.arneplant.logisticainterna_kot2.model.dto.PrepaqueteSeccionDTO
import com.arneplant.logisticainterna_kot2.model.dto.UbicacionTarea
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IOrdenFabricacionService {
    @GET("api/ordenesFabricacion/buscarOperaciones/{codigoEtiqueta}/{codigoMaquina}")
    fun buscarOperacionesPorPrepaqueteMaquina(@Path("codigoEtiqueta") codigoEtiqueta: String
                                              ,@Path("codigoMaquina") codigoMaquina: String): Call<List<OrdenFabricacionOperacion>>

    @GET("api/barquillas/buscarOperacionesEnSeccion/{codigoEtiqueta}/{codigoSeccion}")
    fun buscarOperacionesPorBarquillaSeccion(@Path("codigoEtiqueta") codigoEtiqueta: String
                                              ,@Path("codigoSeccion") codSeccion: String): Call<List<OrdenFabricacionOperacion>>

    @GET("api/barquillas/buscarInformacionEnSeccion/{codigoEtiqueta}/{codigoSeccion}")
    fun buscarInformacionPorBarquillaSeccion(@Path("codigoEtiqueta") codigoEtiqueta: String
                                             ,@Path("codigoSeccion") codSeccion: String): Call<List<PrepaqueteSeccionDTO>>

    @POST("api/barquillas/consumirOperacion")
    fun consumirBarquillaOperacion(@Body body: BodyConsumirOperacionBarquilla): Call<List<Consumo>>

    @GET("api/ordenesFabricacion/obtenerUbicacionesTareaPorBarquilla/{codigoEtiqueta}/{codSeccion}")
    fun obtenerUbicacionesTareaPorBarquilla(@Path("codigoEtiqueta") codigoEtiqueta :String,
                                            @Path("codSeccion") codSeccion :String): Call<List<UbicacionTarea>>

    @GET("api/ordenesFabricacion/obtenerOperacionesPorBarquilla/{codigoEtiqueta}")
    fun obtenerOperacionesPorBarquilla(@Path("codigoEtiqueta") codigoEtiqueta :String): Call<List<OrdenFabricacionOperacion>>
}
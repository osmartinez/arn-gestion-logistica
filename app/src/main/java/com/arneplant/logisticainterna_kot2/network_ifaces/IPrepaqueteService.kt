package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.OrdenFabricacionOperacion
import com.arneplant.logisticainterna_kot2.model.dto.PrepaqueteInfo
import com.arneplant.logisticainterna_kot2.model.dto.PrepaqueteSeccionDTO
import retrofit2.Call
import retrofit2.http.*

interface IPrepaqueteService {
    @GET("api/prepaquetes/{codigoEtiqueta}")
    fun buscarPorCodigo(@Path("codigoEtiqueta") codigoEtiqueta: String)
                                             : Call<List<PrepaqueteInfo>>

    @GET("api/prepaquetes/buscarEnSeccion")
    fun buscarEnSeccion(@Query("codigoEtiqueta") codigoEtiqueta: String,@Query("codigoSeccion") codigoSeccion: String)
            : Call<List<PrepaqueteSeccionDTO>>

    @POST("api/prepaquetes/actualizarCantidad")
    fun actualizarCantidad(@Body prepaquete: PrepaqueteInfo)
            : Call<List<PrepaqueteInfo>>
}
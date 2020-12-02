package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.OrdenFabricacionOperacion
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IOrdenFabricacionService {
    @GET("api/ordenesFabricacion/buscarOperaciones/{codigoEtiqueta}/{codigoMaquina}")
    fun buscarOperacionesPorPrepaqueteMaquina(@Path("codigoEtiqueta") codigoEtiqueta: String
                                              ,@Path("codigoMaquina") codigoMaquina: String): Call<List<OrdenFabricacionOperacion>>
}
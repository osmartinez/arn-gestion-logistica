package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.Maquina
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMaquinaService {
    @GET("api/maquinas/{codigoMaquina}")
    abstract fun findMaquinaByCodigoEtiqueta(@Path("codigoMaquina") codigo: String): Call<Maquina>
}
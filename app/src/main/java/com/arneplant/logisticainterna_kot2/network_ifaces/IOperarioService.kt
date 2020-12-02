package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.Operario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IOperarioService {
    @GET("api/operarios/buscarPorCodigo/{codigo}")
    fun buscarPorCodigo(@Path("codigo") codigo: String): Call<Operario>
}
package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.Labor
import com.arneplant.logisticainterna_kot2.model.Maquina
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IOperacionService {
    @GET("api/operacion/labores")
    fun findLabores(): Call<List<Labor>>
}
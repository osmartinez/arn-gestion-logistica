package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.MaquinaColaTrabajo
import com.arneplant.logisticainterna_kot2.model.dto.AsignacionTareaEjecucion
import com.arneplant.logisticainterna_kot2.model.dto.AsignacionTareaProgramacion
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IMaquinaService {
    @GET("api/maquinas/{codigoMaquina}")
    fun findMaquinaByCodigoEtiqueta(@Path("codigoMaquina") codigo: String): Call<Maquina>

    @POST("api/maquinas/asignarTareaEjecucion")
    fun asignarTareaEjecucion(@Body asginacion: AsignacionTareaEjecucion): Call<List<MaquinaColaTrabajo>>

    @POST("api/maquinas/programarTareaCola")
    fun programarTareaCola(@Body asginacion: AsignacionTareaProgramacion): Call<List<MaquinaColaTrabajo>>
}
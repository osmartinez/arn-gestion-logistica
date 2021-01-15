package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.MaquinaColaTrabajo
import com.arneplant.logisticainterna_kot2.model.dto.AsignacionTareaEjecucion
import com.arneplant.logisticainterna_kot2.model.dto.AsignacionTareaProgramacion
import retrofit2.Call
import retrofit2.http.*

interface IMaquinaService {
    @GET("api/maquinas/{codigoMaquina}")
    fun findMaquinaByCodigoEtiqueta(@Path("codigoMaquina") codigo: String): Call<Maquina>

    @POST("api/maquinas/asignarTareaEjecucion")
    fun asignarTareaEjecucion(@Body asginacion: AsignacionTareaEjecucion): Call<List<MaquinaColaTrabajo>>


    @POST("api/maquinas/desasignarTareaEjecucion")
    fun desasignarTareaEjecucion(@Body asginacion: AsignacionTareaEjecucion): Call<List<MaquinaColaTrabajo>>


    @POST("api/maquinas/programarTareaCola")
    fun programarTareaCola(@Body asginacion: AsignacionTareaProgramacion): Call<List<MaquinaColaTrabajo>>


    @GET("api/maquinas/colaTrabajo/verPorId")
    fun verColaTrabajoPorId(@Query("idMaquina") idMaquina:Int): Call<List<MaquinaColaTrabajo>>

    @GET("api/maquinas/colaTrabajo/verPorCodigo")
    fun verColaTrabajoPorCodigo(@Query("codigoEtiqueta") codigoEtiqueta: String): Call<List<MaquinaColaTrabajo>>
}
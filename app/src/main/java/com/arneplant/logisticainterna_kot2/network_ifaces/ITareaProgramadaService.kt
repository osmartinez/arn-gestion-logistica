package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.TareaPendiente
import com.arneplant.logisticainterna_kot2.model.dto.Consumo
import com.arneplant.logisticainterna_kot2.model.dto.Desconsumo
import com.arneplant.logisticainterna_kot2.model.dto.MaquinaEtiqueta
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ITareaProgramadaService {
    @GET("api/tareasProgramadas/{codigoMaquina}")
    fun listarTareasMaquina(@Path("codigoMaquina") codigoMaquina: String)
            : Call<List<TareaPendiente>>

    @POST("api/tareasProgramadas/programarEnMaquina")
    fun programarTareaMaquina(@Body maquinaEtiqueta: MaquinaEtiqueta)
            : Call<Void>

    @POST("api/tareasProgramadas/consumirEnMaquina")
    fun consumirEnMaquina(@Body maquinaEtiqueta: MaquinaEtiqueta)
            : Call<List<Consumo>>

    @POST("api/tareasProgramadas/desconsumirEnMaquina")
    fun desconsumirEnMaquina(@Body maquinaEtiqueta: MaquinaEtiqueta)
            : Call<Void>

    @POST("api/tareasProgramadas/desconsumirEtiqueta")
    fun desconsumirEtiqueta(@Body desconsumo: Desconsumo)
            : Call<Void>
}
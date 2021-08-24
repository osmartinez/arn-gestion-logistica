package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.LaborOperario
import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.Operario
import com.arneplant.logisticainterna_kot2.model.dto.EliminarLaborOperario
import com.arneplant.logisticainterna_kot2.model.dto.RegistrarLaborOperario
import retrofit2.Call
import retrofit2.http.*

interface IOperarioService {
    @GET("api/operarios/buscarPorCodigo/{codigo}")
    fun buscarPorCodigo(@Path("codigo") codigo: String): Call<Operario>

    @GET("api/operarios/labores/{idOperario}")
    fun obtenerLaboresTurnoActual(@Path("idOperario") idOperario: Int): Call<List<LaborOperario>>

    @POST("api/operarios/labores/registrar")
    fun registrarLabor(@Body registrarBody: RegistrarLaborOperario): Call<List<LaborOperario>>

    @HTTP(method="DELETE", path="api/operarios/labores/eliminar", hasBody = true)
    fun eliminarLabor(@Body eliminarBody: EliminarLaborOperario): Call<List<LaborOperario>>

}
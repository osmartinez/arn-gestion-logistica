package com.arneplant.logisticainterna_kot2.network_ifaces

import com.arneplant.logisticainterna_kot2.model.OrdenFabricacionOperacionTallaCantidad
import com.arneplant.logisticainterna_kot2.model.Respuesta
import com.arneplant.logisticainterna_kot2.model.TareaPendiente
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IGestionPaqueteMaquina {
    @GET("gestionPaquetesMaquinas/listarTareasMaquina/{codMaquina}")
    abstract fun listarTareasMaquina(@Path("codMaquina") codMaquina: String)
            : Call<List<TareaPendiente>>

    @GET("gestionPaquetesMaquinas/intentarInsertarPaquete/{codMaquina}/{codPrepaquete}/{esAgrupacion}")
    abstract fun intentarInsertarPaquete(@Path("codMaquina") codMaquina: String,
                                     @Path("codPrepaquete") codPrepaquete: String,
                                     @Path("esAgrupacion") esAgrupacion: Int)
            : Call<Respuesta>

    @GET("gestionPaquetesMaquinas/programarTareas/{tareas}/{idMaquina}/{esAgrupacion}")
    abstract fun programarTareas( @Path("tareas") tareas:String ,
                                  @Path("idMaquina") idMaquina:Int,
                                  @Path("esAgrupacion") esAgrupacion:Boolean)
            : Call<Respuesta>

    @GET("gestionPaquetesMaquinas/consumirEtiquetaEnMaquina/{codigoEtiqueta}/{codigoMaquina}")
    abstract fun consumirEtiquetaEnMaquina( @Path("codigoEtiqueta") codigoEtiqueta:String ,
                                  @Path("codigoMaquina") codigoMaquina:String)
            : Call<Respuesta>

    @GET("gestionPaquetesMaquinas/desconsumirEtiquetas/{codigosEtiquetas}/{idMaquina}")
    abstract fun desconsumirEtiquetas( @Path("codigosEtiquetas") codigosEtiquetas:String ,
                                       @Path("idMaquina") idMaquina:Int)
            : Call<Respuesta>

}


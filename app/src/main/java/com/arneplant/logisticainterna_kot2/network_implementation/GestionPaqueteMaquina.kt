package com.arneplant.logisticainterna_kot2.network_implementation

import com.arneplant.logisticainterna_kot2.model.OrdenFabricacionOperacionTallaCantidad
import com.arneplant.logisticainterna_kot2.model.Respuesta
import com.arneplant.logisticainterna_kot2.model.TareaPendiente
import com.arneplant.logisticainterna_kot2.network.RetrofitInstance
import com.arneplant.logisticainterna_kot2.network_ifaces.IGestionPaqueteMaquina
import retrofit2.Call

class GestionPaqueteMaquina:IGestionPaqueteMaquina {



    private var service: IGestionPaqueteMaquina = RetrofitInstance.getRetrofitInstance()!!.create(IGestionPaqueteMaquina::class.java)

    override fun listarTareasMaquina(codMaquina: String): Call<List<TareaPendiente>> {
        return service.listarTareasMaquina(codMaquina)
    }

    override fun intentarInsertarPaquete(
        codMaquina: String,
        codPrepaquete: String,
        esAgrupacion: Int
    ): Call<Respuesta> {
        return service.intentarInsertarPaquete(codMaquina,codPrepaquete,esAgrupacion)
    }

    override fun programarTareas(
        tareas: String,
        idMaquina: Int,
        esAgrupacion: Boolean
    ): Call<Respuesta> {
        return service.programarTareas(tareas,idMaquina,esAgrupacion)
    }

    override fun consumirEtiquetaEnMaquina(
        codigoEtiqueta: String,
        codigoMaquina: String
    ): Call<Respuesta> {
        return service.consumirEtiquetaEnMaquina(codigoEtiqueta, codigoMaquina)
    }

    override fun desconsumirEtiquetas(codigosEtiquetas: String, idMaquina: Int): Call<Respuesta> {
        return service.desconsumirEtiquetas(codigosEtiquetas, idMaquina)
    }

}
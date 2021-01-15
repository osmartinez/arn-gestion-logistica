package com.arneplant.logisticainterna_kot2.network_implementation

import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.MaquinaColaTrabajo
import com.arneplant.logisticainterna_kot2.model.dto.AsignacionTareaEjecucion
import com.arneplant.logisticainterna_kot2.model.dto.AsignacionTareaProgramacion
import com.arneplant.logisticainterna_kot2.network.RetrofitInstance
import com.arneplant.logisticainterna_kot2.network_ifaces.IMaquinaService
import retrofit2.Call

class MaquinaService:IMaquinaService {
    override fun desasignarTareaEjecucion(asginacion: AsignacionTareaEjecucion): Call<List<MaquinaColaTrabajo>> {
        return service.desasignarTareaEjecucion(asginacion)
    }

    override fun verColaTrabajoPorCodigo(codigoEtiqueta: String): Call<List<MaquinaColaTrabajo>> {
        return service.verColaTrabajoPorCodigo(codigoEtiqueta)
    }

    override fun verColaTrabajoPorId(idMaquina: Int): Call<List<MaquinaColaTrabajo>> {
        return service.verColaTrabajoPorId(idMaquina)
    }

    override fun programarTareaCola(asginacion: AsignacionTareaProgramacion): Call<List<MaquinaColaTrabajo>> {
        return service.programarTareaCola(asginacion)
    }

    override fun asignarTareaEjecucion(asginacion: AsignacionTareaEjecucion): Call<List<MaquinaColaTrabajo>> {
        return service.asignarTareaEjecucion(asginacion)
    }

    private var service: IMaquinaService = RetrofitInstance.getRetrofitInstance()!!.create(
        IMaquinaService::class.java)

    override fun findMaquinaByCodigoEtiqueta(codigo: String): Call<Maquina> {
        return service.findMaquinaByCodigoEtiqueta(codigo)
    }
}
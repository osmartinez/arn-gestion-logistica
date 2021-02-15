package com.arneplant.logisticainterna_kot2.util

import com.arneplant.logisticainterna_kot2.model.MaquinaColaTrabajo
import com.arneplant.logisticainterna_kot2.model.dto.AgrupacionCola
import java.util.HashMap

object Utils {
    fun getTipo(cod: String): Tipo {
        return if (cod.startsWith("01")) {
            Tipo.Contenedor
        } else if (cod.startsWith("00")) {
            Tipo.OF
        } else if (cod.startsWith("R")) {
            Tipo.Articulo
        } else if (cod.startsWith("03")) {
            Tipo.Ubicacion
        } else if (cod.startsWith("04")) {
            Tipo.PrePaquete
        } else if (cod.startsWith("11")) {
            Tipo.PrePaqueteAgrupacion
        } else if (cod.startsWith("12")) {
            Tipo.Tren
        } else if (cod.startsWith("192.")) {
            Tipo.IP
        } else if (cod.startsWith("19") || cod.startsWith("18")) {
            Tipo.CajaEnvio
        } else if (cod.startsWith("08")) {
            Tipo.EspacioVagon
        } else if (cod.startsWith("02")) {
            Tipo.Maquina
        } else if (cod.startsWith("05")) {
            Tipo.Barquilla
        } else {
            Tipo.None
        }
    }

    fun agruparColaTrabajo(cola: List<MaquinaColaTrabajo>): ArrayList<AgrupacionCola> {
        var tareas: ArrayList<AgrupacionCola> = ArrayList()
        val mapa = HashMap<Int, ArrayList<MaquinaColaTrabajo>>()
        for (tarea in cola) {
            if (!mapa.containsKey(tarea.agrupacion)) {
                val list = java.util.ArrayList<MaquinaColaTrabajo>()
                list.add(tarea)
                mapa[tarea.agrupacion] = list
            } else {
                mapa[tarea.agrupacion]!!.add(tarea)
            }
        }

        for (par in mapa) {
            if (par.key != 0) {
                tareas.add(AgrupacionCola(par.value))
            } else {
                for (tarea in par.value) {
                    val listaUnica = ArrayList<MaquinaColaTrabajo>()
                    listaUnica.add(tarea)
                    tareas.add(AgrupacionCola(listaUnica))
                }
            }
        }

        tareas.sortBy { x -> x.posicion }
        return tareas
    }

    fun shortNombreCliente(cliente: String): String {
        if (cliente.length > 20) {
            return cliente.take(20)
        }
        return cliente
    }

    fun shortModelo(modelo: String): String {
        if (modelo.length > 17) {
            return modelo.take(17)
        }
        return modelo
    }
}
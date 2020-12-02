package com.arneplant.logisticainterna_kot2.util

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
        } else {
            Tipo.None
        }
    }
}
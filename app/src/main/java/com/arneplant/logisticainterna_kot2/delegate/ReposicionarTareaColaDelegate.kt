package com.arneplant.logisticainterna_kot2.delegate

import com.arneplant.logisticainterna_kot2.model.MaquinaColaTrabajo
import com.arneplant.logisticainterna_kot2.model.dto.AgrupacionCola

interface ReposicionarTareaColaDelegate {
    fun subir(tarea: AgrupacionCola)
    fun bajar(tarea: AgrupacionCola)
}
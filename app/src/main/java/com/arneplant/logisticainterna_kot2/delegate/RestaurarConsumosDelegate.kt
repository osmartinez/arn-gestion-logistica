package com.arneplant.logisticainterna_kot2.delegate

import com.arneplant.logisticainterna_kot2.model.Maquina
import com.arneplant.logisticainterna_kot2.model.dto.Consumo

interface RestaurarConsumosDelegate {
    fun desconsumir(maquina: Maquina)

}
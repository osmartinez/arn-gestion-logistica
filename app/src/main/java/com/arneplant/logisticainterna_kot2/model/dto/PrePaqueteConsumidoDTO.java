package com.arneplant.logisticainterna_kot2.model.dto;

import java.io.Serializable;

public class PrePaqueteConsumidoDTO implements Serializable {
    private int id;
    private OrdenFabricacionOperacionDTO operacion;
    public PrePaqueteConsumidoDTO() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public OrdenFabricacionOperacionDTO getOperacion() {
        return operacion;
    }
    public void setOperacion(OrdenFabricacionOperacionDTO operacion) {
        this.operacion = operacion;
    }


}
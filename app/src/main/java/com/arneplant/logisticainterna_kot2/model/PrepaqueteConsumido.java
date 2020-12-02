package com.arneplant.logisticainterna_kot2.model;


import java.io.Serializable;

class PrepaqueteConsumido implements Serializable {
    private Integer id;
    private OrdenFabricacionOperacion operacion;
    private PrePaquete prepaquete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrdenFabricacionOperacion getOperacion() {
        return operacion;
    }

    public void setOperacion(OrdenFabricacionOperacion operacion) {
        this.operacion = operacion;
    }

    public PrePaquete getPrepaquete() {
        return prepaquete;
    }

    public void setPrepaquete(PrePaquete prepaquete) {
        this.prepaquete = prepaquete;
    }
}

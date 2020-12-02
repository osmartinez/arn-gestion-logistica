package com.arneplant.logisticainterna_kot2.model;

import java.io.Serializable;
import java.util.List;

public class OrdenFabricacionOperacionTalla implements Serializable {
    private Integer id;
    private List<OrdenFabricacionOperacionTallaCantidad> ordenFabOperacionesTallasCantidad;
    private String tallas;
    private String tallaUtillaje;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTallas() {
        return tallas;
    }

    public void setTallas(String tallas) {
        this.tallas = tallas;
    }

    public String getTallaUtillaje() {
        return tallaUtillaje;
    }

    public void setTallaUtillaje(String tallaUtillaje) {
        this.tallaUtillaje = tallaUtillaje;
    }

    public List<OrdenFabricacionOperacionTallaCantidad> getOrdenFabOperacionesTallasCantidad() {
        return ordenFabOperacionesTallasCantidad;
    }

    public void setOrdenFabOperacionesTallasCantidad(
            List<OrdenFabricacionOperacionTallaCantidad> ordenFabOperacionesTallasCantidad) {
        this.ordenFabOperacionesTallasCantidad = ordenFabOperacionesTallasCantidad;
    }

}

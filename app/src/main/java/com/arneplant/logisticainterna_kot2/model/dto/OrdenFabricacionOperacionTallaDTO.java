package com.arneplant.logisticainterna_kot2.model.dto;

import java.io.Serializable;
import java.util.List;

public class OrdenFabricacionOperacionTallaDTO implements Serializable {
    private int id;
    private String tallas;
    private String tallaUtillaje;
    private List<OrdenFabricacionOperacionTallaCantidadDTO> operacionesTallasCantidad;

    public OrdenFabricacionOperacionTallaDTO() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<OrdenFabricacionOperacionTallaCantidadDTO> getOperacionesTallasCantidad() {
        return operacionesTallasCantidad;
    }

    public void setOperacionesTallasCantidad(List<OrdenFabricacionOperacionTallaCantidadDTO> operacionesTallasCantidad) {
        this.operacionesTallasCantidad = operacionesTallasCantidad;
    }
}
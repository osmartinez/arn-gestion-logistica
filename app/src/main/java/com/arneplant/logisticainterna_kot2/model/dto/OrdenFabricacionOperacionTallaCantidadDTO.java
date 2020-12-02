package com.arneplant.logisticainterna_kot2.model.dto;

import com.arneplant.logisticainterna_kot2.model.Maquina;

import java.io.Serializable;

public class OrdenFabricacionOperacionTallaCantidadDTO implements Serializable {
    private int id;
    private int estado;
    private Maquina maquina;

    public OrdenFabricacionOperacionTallaCantidadDTO() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public Maquina getMaquina() {
        return maquina;
    }
    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }


}
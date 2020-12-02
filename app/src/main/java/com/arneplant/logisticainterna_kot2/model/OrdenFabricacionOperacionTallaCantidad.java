package com.arneplant.logisticainterna_kot2.model;

import java.io.Serializable;

public class OrdenFabricacionOperacionTallaCantidad implements Serializable {
    private Integer id;
    private OrdenFabricacionOperacionTalla ordenFabricacionOperacionTalla;
    private Double cantidadFabricar;
    private Double cantidadSaldos;
    private Double cantidadProducida;
    private Double cantidadDefectuosa;
    private Integer estado;
    private Boolean finalizado;
    private Maquina maquina;

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrdenFabricacionOperacionTalla getOrdenFabricacionOperacionTalla() {
        return ordenFabricacionOperacionTalla;
    }

    public void setOrdenFabricacionOperacionTalla(OrdenFabricacionOperacionTalla ordenFabricacionOperacionTalla) {
        this.ordenFabricacionOperacionTalla = ordenFabricacionOperacionTalla;
    }

    public Double getCantidadFabricar() {
        return cantidadFabricar;
    }

    public void setCantidadFabricar(Double cantidadFabricar) {
        this.cantidadFabricar = cantidadFabricar;
    }

    public Double getCantidadSaldos() {
        return cantidadSaldos;
    }

    public void setCantidadSaldos(Double cantidadSaldos) {
        this.cantidadSaldos = cantidadSaldos;
    }

    public Double getCantidadProducida() {
        return cantidadProducida;
    }

    public void setCantidadProducida(Double cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }

    public Double getCantidadDefectuosa() {
        return cantidadDefectuosa;
    }

    public void setCantidadDefectuosa(Double cantidadDefectuosa) {
        this.cantidadDefectuosa = cantidadDefectuosa;
    }


    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }
}

package com.arneplant.logisticainterna_kot2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PrePaqueteAgrupacion implements Serializable {
    private String codigoEtiqueta;
    private Integer numeroAgrupacion;
    private String talla;
    private Double cantidad;
    private Date fechaCreacion;
    private List<PrePaquete> paquetes;

    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public Integer getNumeroAgrupacion() {
        return numeroAgrupacion;
    }

    public void setNumeroAgrupacion(Integer numeroAgrupacion) {
        this.numeroAgrupacion = numeroAgrupacion;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<PrePaquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<PrePaquete> paquetes) {
        this.paquetes = paquetes;
    }
}

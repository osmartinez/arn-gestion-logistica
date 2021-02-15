package com.arneplant.logisticainterna_kot2.model.dto;

import com.arneplant.logisticainterna_kot2.model.OrdenFabricacionOperacion;


public class BodyConsumirOperacionBarquilla {
    public String codigoEtiqueta;
    public int idOperacion;
    public int idObrero;
    public int idMaquina;
    public int idBarquillaContenido;
    public double cantidad;
    public String talla;

    public BodyConsumirOperacionBarquilla() {
    }

    public BodyConsumirOperacionBarquilla(OrdenFabricacionOperacion ofo, int idObrero, int idMaquina) {
        this.codigoEtiqueta = ofo.getCodigoEtiqueta();
        this.idOperacion = ofo.getID();
        this.idObrero = idObrero;
        this.idMaquina = idMaquina;
        this.cantidad = ofo.getCantidadConsumir();
        this.talla = ofo.getTallaConsumir();
        this.idBarquillaContenido = ofo.getIdBarquillaContenido();
    }

    public BodyConsumirOperacionBarquilla(String codigoEtiqueta, int idOperacion, int idObrero, int idMaquina, int idBarquillaContenido, double cantidad, String talla) {
        this.codigoEtiqueta = codigoEtiqueta;
        this.idOperacion = idOperacion;
        this.idObrero = idObrero;
        this.idMaquina = idMaquina;
        this.idBarquillaContenido = idBarquillaContenido;
        this.cantidad = cantidad;
        this.talla = talla;
    }

    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public int getIdObrero() {
        return idObrero;
    }

    public void setIdObrero(int idObrero) {
        this.idObrero = idObrero;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public int getIdBarquillaContenido() {
        return idBarquillaContenido;
    }

    public void setIdBarquillaContenido(int idBarquillaContenido) {
        this.idBarquillaContenido = idBarquillaContenido;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
}

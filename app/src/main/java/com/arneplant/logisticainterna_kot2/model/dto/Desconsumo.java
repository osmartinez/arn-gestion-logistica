package com.arneplant.logisticainterna_kot2.model.dto;

public class Desconsumo {
    public String CodigoEtiqueta;
    public int IdOperacion;

    public Desconsumo() {
    }

    public Desconsumo(String codigoEtiqueta, int idOperacion) {
        CodigoEtiqueta = codigoEtiqueta;
        IdOperacion = idOperacion;
    }

    public String getCodigoEtiqueta() {
        return CodigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        CodigoEtiqueta = codigoEtiqueta;
    }

    public int getIdOperacion() {
        return IdOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        IdOperacion = idOperacion;
    }
}

package com.arneplant.logisticainterna_kot2.model.dto;

import java.io.Serializable;

public class RelacionPrepaqueteConsumo implements Serializable {
    private String codigoEtiqueta;
    private int idOperacion;
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

    public RelacionPrepaqueteConsumo() {
    }

    public RelacionPrepaqueteConsumo(String codigoEtiqueta, int idOperacion) {
        this.codigoEtiqueta = codigoEtiqueta;
        this.idOperacion = idOperacion;
    }




}

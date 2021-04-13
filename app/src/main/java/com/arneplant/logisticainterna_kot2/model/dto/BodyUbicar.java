package com.arneplant.logisticainterna_kot2.model.dto;

public class BodyUbicar {
    private String codigoEtiqueta;
    private String codUbicacion;

    public BodyUbicar() {
    }

    public BodyUbicar(String codigoEtiqueta, String codUbicacion) {
        this.codigoEtiqueta = codigoEtiqueta;
        this.codUbicacion = codUbicacion;
    }

    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public String getCodUbicacion() {
        return codUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        this.codUbicacion = codUbicacion;
    }
}

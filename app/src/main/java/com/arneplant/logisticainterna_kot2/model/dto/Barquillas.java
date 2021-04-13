package com.arneplant.logisticainterna_kot2.model.dto;

public class Barquillas {
    private String codigoEtiqueta;
    private int id;
    private String codUbicacion;
    private int agrupacion;

    public Barquillas(String codigoEtiqueta, int id, String codUbicacion, int agrupacion) {
        this.codigoEtiqueta = codigoEtiqueta;
        this.id = id;
        this.codUbicacion = codUbicacion;
        this.agrupacion = agrupacion;
    }

    public Barquillas() {
    }

    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodUbicacion() {
        return codUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        this.codUbicacion = codUbicacion;
    }

    public int getAgrupacion() {
        return agrupacion;
    }

    public void setAgrupacion(int agrupacion) {
        this.agrupacion = agrupacion;
    }
}

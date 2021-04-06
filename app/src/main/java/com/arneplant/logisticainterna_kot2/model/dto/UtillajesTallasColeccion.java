package com.arneplant.logisticainterna_kot2.model.dto;

public class UtillajesTallasColeccion {
    private String CodUtillaje;
    private String Talla;
    private String CodigoEtiqueta;
    private String CodUbicacion;

    public UtillajesTallasColeccion() {
    }

    public String getCodUtillaje() {
        return CodUtillaje;
    }

    public void setCodUtillaje(String codUtillaje) {
        CodUtillaje = codUtillaje;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String talla) {
        Talla = talla;
    }

    public String getCodigoEtiqueta() {
        return CodigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        CodigoEtiqueta = codigoEtiqueta;
    }

    public String getCodUbicacion() {
        return CodUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        CodUbicacion = codUbicacion;
    }
}

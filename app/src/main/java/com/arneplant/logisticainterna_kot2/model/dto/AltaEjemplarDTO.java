package com.arneplant.logisticainterna_kot2.model.dto;

public class AltaEjemplarDTO {
    private String codigoEtiqueta;
    private String talla;
    private String codUtillaje;
    private String idContenedor;

    public AltaEjemplarDTO() {
    }

    public AltaEjemplarDTO(String codigoEtiqueta, String talla, String codUtillaje, String idContenedor) {
        this.codigoEtiqueta = codigoEtiqueta;
        this.talla = talla;
        this.codUtillaje = codUtillaje;
        this.idContenedor = idContenedor;
    }

    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getCodUtillaje() {
        return codUtillaje;
    }

    public void setCodUtillaje(String codUtillaje) {
        this.codUtillaje = codUtillaje;
    }

    public String getIdContenedor() {
        return idContenedor;
    }

    public void setIdContenedor(String idContenedor) {
        this.idContenedor = idContenedor;
    }
}

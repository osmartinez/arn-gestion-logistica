package com.arneplant.logisticainterna_kot2.model.dto;

public class UtillajeUbicacion {
    private String CodUtillaje;
    private String TallaUtillaje;
    private String CodigoEtiqueta;
    private String CodUbicacion;
    private String Ubicacion;

    public UtillajeUbicacion() {
    }

    public String getCodUtillaje() {
        return CodUtillaje;
    }

    public void setCodUtillaje(String codUtillaje) {
        CodUtillaje = codUtillaje;
    }

    public String getTallaUtillaje() {
        return TallaUtillaje;
    }

    public void setTallaUtillaje(String tallaUtillaje) {
        TallaUtillaje = tallaUtillaje;
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

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }
}

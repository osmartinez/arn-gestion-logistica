package com.arneplant.logisticainterna_kot2.model;

import java.io.Serializable;

public class Ubicacion implements Serializable {
    private String codUbicacion;

    private String descripcion;


    public String getCodUbicacion() {
        return codUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        this.codUbicacion = codUbicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}

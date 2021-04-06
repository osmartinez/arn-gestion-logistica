package com.arneplant.logisticainterna_kot2.model;

import java.io.Serializable;
import java.util.Objects;

public class Ubicacion implements Serializable {
    private String CodUbicacion;
    private String Descripcion;


    public Ubicacion() {
    }

    public String getCodUbicacion() {
        return CodUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        CodUbicacion = codUbicacion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return Objects.equals(CodUbicacion, ubicacion.getCodUbicacion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(CodUbicacion);
    }
}

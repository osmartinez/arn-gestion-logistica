package com.arneplant.logisticainterna_kot2.model;

import java.io.Serializable;

public class Operario implements Serializable {
    private int Id;
    private String CodigoObrero;
    private String Nombre;
    private String Apellidos;
    private boolean EsResponsable;
    private String CodigoEtiqueta;
    private String Clave;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCodigoObrero() {
        return CodigoObrero;
    }

    public void setCodigoObrero(String codigoObrero) {
        CodigoObrero = codigoObrero;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public boolean isEsResponsable() {
        return EsResponsable;
    }

    public void setEsResponsable(boolean esResponsable) {
        EsResponsable = esResponsable;
    }

    public String getCodigoEtiqueta() {
        return CodigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        CodigoEtiqueta = codigoEtiqueta;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }
}

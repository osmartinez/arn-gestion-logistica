package com.arneplant.logisticainterna_kot2.model;

import java.io.Serializable;

public class Seccion implements Serializable {
    private String codSeccion;
    private String nombre;
    private String esMolde;
    private String esCorte;
    private String grupo;
    private String codigoEtiqueta;

    public String getCodSeccion() {
        return codSeccion;
    }

    public void setCodSeccion(String codSeccion) {
        this.codSeccion = codSeccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEsMolde() {
        return esMolde;
    }

    public void setEsMolde(String esMolde) {
        this.esMolde = esMolde;
    }

    public String getEsCorte() {
        return esCorte;
    }

    public void setEsCorte(String esCorte) {
        this.esCorte = esCorte;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }


    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)return false;
        if(obj == this) return true;
        if(!(obj instanceof Seccion))return false;
        Seccion _obj = (Seccion) obj;
        return _obj.getCodSeccion().equals(this.codSeccion);
    }
}

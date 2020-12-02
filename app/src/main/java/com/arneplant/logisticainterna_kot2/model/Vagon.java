package com.arneplant.logisticainterna_kot2.model;

import java.io.Serializable;
import java.util.List;

public class Vagon implements Serializable {
    private int id;

    private int posicion;

    private String codigoEtiqueta;

    private Tren tren;

    private List<EspacioVagon> espacios;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public Tren getTren() {
        return tren;
    }

    public void setTren(Tren tren) {
        this.tren = tren;
    }

    public List<EspacioVagon> getEspacios() {
        return espacios;
    }

    public void setEspacios(List<EspacioVagon> espacios) {
        this.espacios = espacios;
    }



}

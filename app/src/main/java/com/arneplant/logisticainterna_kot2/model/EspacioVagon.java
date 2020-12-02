package com.arneplant.logisticainterna_kot2.model;

import java.io.Serializable;
import java.util.List;

public class EspacioVagon implements Serializable {

    private int id;

    private String codigoEtiqueta;

    private Vagon vagon;

    private List<PrePaquete> paquetes;

    public List<PrePaquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<PrePaquete> paquetes) {
        this.paquetes = paquetes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public Vagon getVagon() {
        return vagon;
    }

    public void setVagon(Vagon vagon) {
        this.vagon = vagon;
    }



}

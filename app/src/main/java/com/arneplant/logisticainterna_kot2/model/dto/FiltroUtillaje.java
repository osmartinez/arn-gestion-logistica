package com.arneplant.logisticainterna_kot2.model.dto;

public class FiltroUtillaje {
    private String prefijo;
    private boolean activo;

    public FiltroUtillaje(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

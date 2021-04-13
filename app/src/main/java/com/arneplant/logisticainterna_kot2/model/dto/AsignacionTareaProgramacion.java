package com.arneplant.logisticainterna_kot2.model.dto;

public class AsignacionTareaProgramacion {
    private int idTarea;
    private int idMaquina;
    private int agrupacion;
    private int posicion;
    private int idOperarioPrograma;
    private String codigoEtiqueta;

    public AsignacionTareaProgramacion(int idTarea, int idMaquina, int agrupacion,int posicion, int idOperarioPrograma,String codigoEtiqueta) {
        this.idTarea = idTarea;
        this.idMaquina = idMaquina;
        this.agrupacion = agrupacion;
        this.posicion = posicion;
        this.idOperarioPrograma = idOperarioPrograma;
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public AsignacionTareaProgramacion() {
    }

    public int getIdOperarioPrograma() {
        return idOperarioPrograma;
    }

    public void setIdOperarioPrograma(int idOperarioEjecucion) {
        this.idOperarioPrograma = idOperarioEjecucion;
    }

    public int getAgrupacion() {
        return agrupacion;
    }

    public void setAgrupacion(int agrupacion) {
        this.agrupacion = agrupacion;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
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
}

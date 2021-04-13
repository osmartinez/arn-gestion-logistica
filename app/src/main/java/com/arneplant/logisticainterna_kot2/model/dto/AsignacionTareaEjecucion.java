package com.arneplant.logisticainterna_kot2.model.dto;

public class AsignacionTareaEjecucion {
    private int idTarea;
    private int idMaquina;
    private int agrupacion;
    private int idOperarioEjecucion;

    public AsignacionTareaEjecucion(int idTarea, int idMaquina, int agrupacion, int idOperarioEjecucion) {
        this.idTarea = idTarea;
        this.idMaquina = idMaquina;
        this.agrupacion = agrupacion;
        this.idOperarioEjecucion = idOperarioEjecucion;
    }

    public AsignacionTareaEjecucion() {
    }

    public int getIdOperarioEjecucion() {
        return idOperarioEjecucion;
    }

    public void setIdOperarioEjecucion(int idOperarioEjecucion) {
        this.idOperarioEjecucion = idOperarioEjecucion;
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
}

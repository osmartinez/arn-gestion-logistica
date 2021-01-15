package com.arneplant.logisticainterna_kot2.model.dto;

public class AsignacionTareaProgramacion {
    private String idsTareas;
    private int idMaquina;
    private int agrupacion;
    private int idOperarioPrograma;

    public AsignacionTareaProgramacion(String idsTareas, int idMaquina, int agrupacion, int idOperarioPrograma) {
        this.idsTareas = idsTareas;
        this.idMaquina = idMaquina;
        this.agrupacion = agrupacion;
        this.idOperarioPrograma = idOperarioPrograma;
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

    public String getIdsTareas() {
        return idsTareas;
    }

    public void setIdsTareas(String idsTareas) {
        this.idsTareas = idsTareas;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }
}

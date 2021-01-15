package com.arneplant.logisticainterna_kot2.model;

public class MaquinaColaTrabajo {
    private int Id;
    private int IdMaquina;
    private int IdTarea;
    private int Posicion;
    private int Agrupacion;
    private boolean Ejecucion;
    private Integer IdOperarioEjecuta;
    private Integer IdOperarioPrograma;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdMaquina() {
        return IdMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        IdMaquina = idMaquina;
    }

    public int getIdTarea() {
        return IdTarea;
    }

    public void setIdTarea(int idTarea) {
        IdTarea = idTarea;
    }

    public int getPosicion() {
        return Posicion;
    }

    public void setPosicion(int posicion) {
        Posicion = posicion;
    }

    public int getAgrupacion() {
        return Agrupacion;
    }

    public void setAgrupacion(int agrupacion) {
        Agrupacion = agrupacion;
    }

    public boolean isEjecucion() {
        return Ejecucion;
    }

    public void setEjecucion(boolean ejecucion) {
        Ejecucion = ejecucion;
    }

    public Integer getIdOperarioEjecuta() {
        return IdOperarioEjecuta;
    }

    public void setIdOperarioEjecuta(Integer idOperarioEjecuta) {
        IdOperarioEjecuta = idOperarioEjecuta;
    }

    public Integer getIdOperarioPrograma() {
        return IdOperarioPrograma;
    }

    public void setIdOperarioPrograma(Integer idOperarioPrograma) {
        IdOperarioPrograma = idOperarioPrograma;
    }
}

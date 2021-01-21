package com.arneplant.logisticainterna_kot2.model.dto;

import com.arneplant.logisticainterna_kot2.model.MaquinaColaTrabajo;

import java.util.List;

public class ColaMaquinaActualizada {
    private List<MaquinaColaTrabajo> cola;
    private List<MaquinaColaTrabajo> colaBorrados;
    private int idMaquina;
    private int idBancada;

    public ColaMaquinaActualizada(List<MaquinaColaTrabajo> cola, List<MaquinaColaTrabajo> colaBorrados, int idMaquina, int idBancada) {
        this.cola = cola;
        this.colaBorrados = colaBorrados;
        this.idMaquina = idMaquina;
        this.idBancada = idBancada;
    }

    public List<MaquinaColaTrabajo> getCola() {
        return cola;
    }

    public void setCola(List<MaquinaColaTrabajo> cola) {
        this.cola = cola;
    }

    public List<MaquinaColaTrabajo> getColaBorrados() {
        return colaBorrados;
    }

    public void setColaBorrados(List<MaquinaColaTrabajo> colaBorrados) {
        this.colaBorrados = colaBorrados;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public int getIdBancada() {
        return idBancada;
    }

    public void setIdBancada(int idBancada) {
        this.idBancada = idBancada;
    }
}

package com.arneplant.logisticainterna_kot2.model.dto;

import com.arneplant.logisticainterna_kot2.model.Maquina;

import java.io.Serializable;
import java.util.ArrayList;

public class PrePaquetesMaquina implements Serializable {
    private Maquina maquina;
    private ArrayList<PrePaqueteDTO> prePaquetes;
    private int IdAgrupacionMaquina;

    public PrePaquetesMaquina() {
        this.prePaquetes = new ArrayList<>() ;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public ArrayList<PrePaqueteDTO> getPrePaquetes() {
        return prePaquetes;
    }

    public void setPrePaquetes(ArrayList<PrePaqueteDTO> prePaquetes) {
        this.prePaquetes = prePaquetes;
    }

    public int getIdAgrupacionMaquina() {
        return IdAgrupacionMaquina;
    }

    public void setIdAgrupacionMaquina(int idAgrupacionMaquina) {
        IdAgrupacionMaquina = idAgrupacionMaquina;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PrePaquetesMaquina)) {
            return false;
        }
        PrePaquetesMaquina c = (PrePaquetesMaquina) o;
        return this.maquina.getID()== c.getMaquina().getID();
    }

}
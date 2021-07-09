package com.arneplant.logisticainterna_kot2.model.dto;

import java.util.ArrayList;
import java.util.List;

public class AgrupacionUbicacionTarea {
    private List<UbicacionTarea> ubicaciones;

    public AgrupacionUbicacionTarea(List<UbicacionTarea> ubicaciones){
        this.ubicaciones = ubicaciones;
    }

    public int getNumCajas(){
        int suma = 0;
        for(UbicacionTarea u: this.ubicaciones){
            suma += u.getNumCajas();
        }
        return suma;
    }

    public String getUbicacion(){
        return this.ubicaciones.get(0).getDescripcion();
    }

    public double getPares(){
        double suma = 0;
        for(UbicacionTarea u: this.ubicaciones){
            suma += u.getCantidad();
        }
        return suma;
    }

    public boolean esMaquina(){
        return this.ubicaciones.get(0).isEsMaquina();
    }

    public boolean esEjecucion(){
        return this.ubicaciones.get(0).isEjecucion();
    }

    public String getTalla(){
        return this.ubicaciones.get(0).getTalla();
    }
}

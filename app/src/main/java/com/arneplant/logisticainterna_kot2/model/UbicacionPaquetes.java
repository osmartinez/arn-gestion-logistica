package com.arneplant.logisticainterna_kot2.model;

import androidx.annotation.NonNull;

public class UbicacionPaquetes {

    private String nombre;
    private int numPaquetes;

    public UbicacionPaquetes(String nombre, int numPaquetes) {
        this.nombre = nombre;
        this.numPaquetes = numPaquetes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumPaquetes() {
        return numPaquetes;
    }

    public void setNumPaquetes(int numPaquetes) {
        this.numPaquetes = numPaquetes;
    }

    @NonNull
    @Override
    public String toString() {
        return this.numPaquetes + " paquetes";
    }
}

package com.arneplant.logisticainterna_kot2.model.dto;

import java.util.ArrayList;
import java.util.Objects;

public class MemoriaConsumo {
    private ArrayList<Integer>  idsOrdenes;
    private ArrayList<String> descripciones;

    public MemoriaConsumo(ArrayList<Integer> idsOrdenes, ArrayList<String> descripciones) {
        this.idsOrdenes = idsOrdenes;
        this.descripciones = descripciones;
    }

    public MemoriaConsumo() {
        this.idsOrdenes = new ArrayList<>();
        this.descripciones = new ArrayList<>();
    }

    public ArrayList<Integer> getIdsOrdenes() {
        return idsOrdenes;
    }

    public void setIdsOrdenes(ArrayList<Integer> idsOrdenes) {
        this.idsOrdenes = idsOrdenes;
    }

    public ArrayList<String> getDescripciones() {
        return descripciones;
    }

    public void setDescripciones(ArrayList<String> descripciones) {
        this.descripciones = descripciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemoriaConsumo that = (MemoriaConsumo) o;
        boolean coinciden = true;

        if(that.descripciones.size() != this.descripciones.size() ||
        that.idsOrdenes.size()!= this.idsOrdenes.size()){
            return false;
        }
        for(int i = 0;i<this.descripciones.size();i++){
            if(!this.descripciones.get(i).equals(that.descripciones.get(i))){
                coinciden = false;
            }
        }

        if(!coinciden){return false;}

        for(int i = 0;i<this.idsOrdenes.size();i++){
            if(!this.idsOrdenes.get(i).equals(that.idsOrdenes.get(i))){
                coinciden = false;
            }
        }


        return coinciden;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsOrdenes, descripciones);
    }
}

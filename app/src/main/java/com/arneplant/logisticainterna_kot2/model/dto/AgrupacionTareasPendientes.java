package com.arneplant.logisticainterna_kot2.model.dto;

import com.arneplant.logisticainterna_kot2.model.TareaPendiente;

import java.util.ArrayList;

public class AgrupacionTareasPendientes {
    private String codigoOrden;
    private String nombreCliente;
    private float pares;
    private ArrayList<TareaPendiente> tareas = new ArrayList<>();

    public String getCodigoOrden() {
        if (this.tareas.size() > 0) {
            if (this.tareas.size() == 1) {
                return this.tareas.get(0).getCodigoOrden();
            } else {
                return this.tareas.get(0).getCodigoOrden() + " y " + (this.tareas.size() - 1) + " más";
            }
        } else {
            return "< >";
        }
    }


    public String getNombreCliente() {
        if (this.tareas.size() > 0) {
            if (this.tareas.size() == 1) {
                return this.tareas.get(0).getNombreCliente();
            } else {
                return this.tareas.get(0).getNombreCliente() + " y " + (this.tareas.size() - 1) + " más";
            }
        } else {
            return "< >";
        }
    }


    public float getPares() {
        if (this.tareas.size() > 0) {
            if (this.tareas.size() == 1) {
                return this.tareas.get(0).getParesFabricar();
            } else {
                float p = 0;
                for(TareaPendiente tp: this.tareas){
                    p+=tp.getParesFabricar();
                }
                return p;
            }
        } else {
            return 0;
        }    }


    public ArrayList<TareaPendiente> getTareas() {
        return tareas;
    }



    public AgrupacionTareasPendientes() {

    }

    public AgrupacionTareasPendientes(ArrayList<TareaPendiente> tareas) {
        this.tareas = tareas;
    }


}

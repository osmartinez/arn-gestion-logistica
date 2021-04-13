package com.arneplant.logisticainterna_kot2.model.dto;

import androidx.annotation.NonNull;

import com.arneplant.logisticainterna_kot2.model.MaquinaColaTrabajo;
import com.arneplant.logisticainterna_kot2.model.TareaPendiente;
import com.arneplant.logisticainterna_kot2.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgrupacionCola{
    private String codigoOrden;
    private String nombreCliente;
    private float pares;
    private int agrupacion;
    private boolean isEjecucion;
    private String utillaje;
    private String tallaUtillaje;
    private String modelo;
    private int posicion;

    private ArrayList<MaquinaColaTrabajo> tareas = new ArrayList<>();

    public AgrupacionCola(ArrayList<MaquinaColaTrabajo> tareas) {
        this.tareas = tareas;
    }

    public int getPosicion() {
        if(this.tareas.size()>0){
            return this.tareas.get(0).getPosicion();
        }
        else{
            return 0;
        }
    }

    public int getAgrupacion() {
        if(this.tareas.size()>0){
            return this.tareas.get(0).getAgrupacion();
        }
        else{
            return 0;
        }
    }

    public String getUtillaje() {
        if(this.tareas.size()>0){
            return this.tareas.get(0).getUtillaje();
        }
        else{
            return " < > ";
        }    }

    public String getTallaUtillaje() {
        if(this.tareas.size()>0){
            return this.tareas.get(0).getTallaUtillaje();
        }
        else{
            return " < > ";
        }
    }

    public String getModelo() {
        if(this.tareas.size()>0){
            return Utils.INSTANCE.shortModelo(this.tareas.get(0).getModelo());
        }
        else{
            return "< >";
        }
    }

    public boolean isEjecucion() {
        if(this.tareas.size()>0){
            return this.tareas.get(0).isEjecucion();
        }
        else{return false;}
    }

    public String getCodigoOrden() {
        if (this.tareas.size() > 0) {
            if (this.tareas.size() == 1) {
                return this.tareas.get(0).getCodigoOrden();
            } else {
                return this.tareas.get(0).getCodigoOrden() + " (+" + (this.tareas.size() - 1) + ")";
            }
        } else {
            return "< >";
        }
    }


    public String getNombreCliente() {
        if (this.tareas.size() > 0) {
            if (this.tareas.size() == 1) {
                return Utils.INSTANCE.shortNombreCliente(this.tareas.get(0).getNombreCliente()) ;
            } else {
                return Utils.INSTANCE.shortNombreCliente(this.tareas.get(0).getNombreCliente()) + " (+" + (this.tareas.size() - 1) + ")";
            }
        } else {
            return "< >";
        }
    }

    public String getCodigoEtiqueta(){
        if (this.tareas.size() > 0) {
            return tareas.get(0).getCodigoEtiquetaFichada();
        } else {
            return "";
        }
    }


    public float getPares() {
        if (this.tareas.size() > 0) {
            if (this.tareas.size() == 1) {
                return this.tareas.get(0).getCantidadFabricar();
            } else {
                float p = 0;
                for(MaquinaColaTrabajo tp: this.tareas){
                    p+=tp.getCantidadFabricar();
                }
                return p;
            }
        } else {
            return 0;
        }    }


    public ArrayList<MaquinaColaTrabajo> getTareas() {
        return tareas;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getPosicion()+". "+ this.getCodigoOrden() + "\t" + this.getUtillaje()+ " <"+this.getTallaUtillaje()+">";
    }
}

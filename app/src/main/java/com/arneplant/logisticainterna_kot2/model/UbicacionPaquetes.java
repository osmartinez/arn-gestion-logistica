package com.arneplant.logisticainterna_kot2.model;

import androidx.annotation.NonNull;

import com.arneplant.logisticainterna_kot2.model.dto.Barquillas;
import com.arneplant.logisticainterna_kot2.model.dto.Paquetes;
import com.arneplant.logisticainterna_kot2.model.dto.UtillajesTallasColeccion;

import java.util.ArrayList;
import java.util.Objects;

public class UbicacionPaquetes {

    private String codUbicacion;
    private String nombreUbicacion;
    private boolean seleccionada;
    private ArrayList<UtillajesTallasColeccion> utillajes;
    private ArrayList<Barquillas> barquillas;
    private ArrayList<Paquetes> paquetes;

    public UbicacionPaquetes(Ubicacion ub){
        this.nombreUbicacion = ub.getDescripcion();
        this.codUbicacion = ub.getCodUbicacion();
        this.utillajes = new ArrayList();
        this.barquillas = new ArrayList();
        this.paquetes =  new ArrayList();
    }

    public UbicacionPaquetes(String nombre){
        this.nombreUbicacion = nombre;
        this.utillajes = new ArrayList();
        this.barquillas = new ArrayList();
        this.paquetes =  new ArrayList();
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
    }

    public ArrayList<UtillajesTallasColeccion> getUtillajes() {
        return utillajes;
    }

    public void setUtillajes(ArrayList<UtillajesTallasColeccion> utillajes) {
        this.utillajes = utillajes;
    }

    public ArrayList<Barquillas> getBarquillas() {
        return barquillas;
    }

    public void setBarquillas(ArrayList<Barquillas> barquillas) {
        this.barquillas = barquillas;
    }

    public ArrayList<Paquetes> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(ArrayList<Paquetes> paquetes) {
        this.paquetes = paquetes;
    }

    public String getCodUbicacion() {
        return codUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        this.codUbicacion = codUbicacion;
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UbicacionPaquetes that = (UbicacionPaquetes) o;
        return codUbicacion.equals(that.getCodUbicacion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(codUbicacion);
    }
}

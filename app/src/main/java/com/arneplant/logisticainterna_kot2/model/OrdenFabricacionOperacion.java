package com.arneplant.logisticainterna_kot2.model;

import java.io.Serializable;
import java.util.List;

public class OrdenFabricacionOperacion implements Serializable {
    private int ID;
    private OrdenFabricacion ordenFabricacion;
    private String NumeroOperacion;
    private String NumeroOperacionAnterior;
    private String NumeroOperacionSiguiente;
    private String Descripcion;
    private Seccion Seccion;
    private String CodSeccion;
    private Utillaje Utillaje;
    private String CodConexion;
    private List<OrdenFabricacionOperacionTalla> OrdenFabOperacionesTallas;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public OrdenFabricacion getOrdenFabricacion() {
        return ordenFabricacion;
    }

    public void setOrdenFabricacion(OrdenFabricacion ordenFabricacion) {
        this.ordenFabricacion = ordenFabricacion;
    }

    public String getNumeroOperacion() {
        return NumeroOperacion;
    }

    public void setNumeroOperacion(String numeroOperacion) {
        NumeroOperacion = numeroOperacion;
    }

    public String getNumeroOperacionAnterior() {
        return NumeroOperacionAnterior;
    }

    public void setNumeroOperacionAnterior(String numeroOperacionAnterior) {
        NumeroOperacionAnterior = numeroOperacionAnterior;
    }

    public String getNumeroOperacionSiguiente() {
        return NumeroOperacionSiguiente;
    }

    public void setNumeroOperacionSiguiente(String numeroOperacionSiguiente) {
        NumeroOperacionSiguiente = numeroOperacionSiguiente;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public com.arneplant.logisticainterna_kot2.model.Seccion getSeccion() {
        return Seccion;
    }

    public void setSeccion(com.arneplant.logisticainterna_kot2.model.Seccion seccion) {
        Seccion = seccion;
    }

    public String getCodSeccion() {
        return CodSeccion;
    }

    public void setCodSeccion(String codSeccion) {
        CodSeccion = codSeccion;
    }

    public com.arneplant.logisticainterna_kot2.model.Utillaje getUtillaje() {
        return Utillaje;
    }

    public void setUtillaje(com.arneplant.logisticainterna_kot2.model.Utillaje utillaje) {
        Utillaje = utillaje;
    }

    public String getCodConexion() {
        return CodConexion;
    }

    public void setCodConexion(String codConexion) {
        CodConexion = codConexion;
    }

    public List<OrdenFabricacionOperacionTalla> getOrdenFabOperacionesTallas() {
        return OrdenFabOperacionesTallas;
    }

    public void setOrdenFabOperacionesTallas(List<OrdenFabricacionOperacionTalla> ordenFabOperacionesTallas) {
        OrdenFabOperacionesTallas = ordenFabOperacionesTallas;
    }
}

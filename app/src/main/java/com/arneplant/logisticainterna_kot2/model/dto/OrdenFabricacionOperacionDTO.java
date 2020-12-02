package com.arneplant.logisticainterna_kot2.model.dto;

import com.arneplant.logisticainterna_kot2.model.Seccion;

import java.io.Serializable;
import java.util.List;

public class OrdenFabricacionOperacionDTO implements Serializable {
    private int id;
    private String codConexion;
    private Seccion seccion;
    private String codUtillaje;
    private String numeroOperacionAnterior;
    private String numeroOperacion;
    private String numeroOperacionSiguiente;
    private String descripcion;
    private List<OrdenFabricacionOperacionTallaDTO> operacionesTallas;

    public OrdenFabricacionOperacionDTO() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCodConexion() {
        return codConexion;
    }
    public void setCodConexion(String codConexion) {
        this.codConexion = codConexion;
    }


    public String getCodUtillaje() {
        return codUtillaje;
    }
    public void setCodUtillaje(String codUtillaje) {
        this.codUtillaje = codUtillaje;
    }
    public String getNumeroOperacionAnterior() {
        return numeroOperacionAnterior;
    }
    public void setNumeroOperacionAnterior(String numeroOperacionAnterior) {
        this.numeroOperacionAnterior = numeroOperacionAnterior;
    }
    public String getNumeroOperacion() {
        return numeroOperacion;
    }
    public void setNumeroOperacion(String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }
    public String getNumeroOperacionSiguiente() {
        return numeroOperacionSiguiente;
    }
    public void setNumeroOperacionSiguiente(String numeroOperacionSiguiente) {
        this.numeroOperacionSiguiente = numeroOperacionSiguiente;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public List<OrdenFabricacionOperacionTallaDTO> getOperacionesTallas() {
        return operacionesTallas;
    }
    public void setOperacionesTallas(List<OrdenFabricacionOperacionTallaDTO> operacionesTallas) {
        this.operacionesTallas = operacionesTallas;
    }
    public Seccion getSeccion() {
        return seccion;
    }
    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }


}
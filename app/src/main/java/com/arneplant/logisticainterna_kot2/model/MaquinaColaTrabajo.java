package com.arneplant.logisticainterna_kot2.model;

import androidx.annotation.NonNull;

public class MaquinaColaTrabajo {
    private int Id;
    private int IdMaquina;
    private int IdTarea;
    private int Posicion;
    private int Agrupacion;
    private boolean Ejecucion;
    private Integer IdOperarioEjecuta;
    private Integer IdOperarioPrograma;
    private String NombreCliente;
    private String CodigoOrden;
    private float CantidadFabricar;
    private String Utillaje;
    private String TallaUtillaje;
    private String Modelo;
    private String CodigoEtiquetaFichada;

    public String getCodigoEtiquetaFichada() {
        return CodigoEtiquetaFichada;
    }

    public void setCodigoEtiquetaFichada(String codigoEtiquetaFichada) {
        CodigoEtiquetaFichada = codigoEtiquetaFichada;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getUtillaje() {
        return Utillaje;
    }

    public void setUtillaje(String utillaje) {
        Utillaje = utillaje;
    }

    public String getTallaUtillaje() {
        return TallaUtillaje;
    }

    public void setTallaUtillaje(String tallaUtillaje) {
        TallaUtillaje = tallaUtillaje;
    }

    public String getCodigoOrden() {
        return CodigoOrden;
    }

    public void setCodigoOrden(String codigoOrden) {
        CodigoOrden = codigoOrden;
    }

    public float getCantidadFabricar() {
        return CantidadFabricar;
    }

    public void setCantidadFabricar(float cantidadFabricar) {
        CantidadFabricar = cantidadFabricar;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdMaquina() {
        return IdMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        IdMaquina = idMaquina;
    }

    public int getIdTarea() {
        return IdTarea;
    }

    public void setIdTarea(int idTarea) {
        IdTarea = idTarea;
    }

    public int getPosicion() {
        return Posicion;
    }

    public void setPosicion(int posicion) {
        Posicion = posicion;
    }

    public int getAgrupacion() {
        return Agrupacion;
    }

    public void setAgrupacion(int agrupacion) {
        Agrupacion = agrupacion;
    }

    public boolean isEjecucion() {
        return Ejecucion;
    }

    public void setEjecucion(boolean ejecucion) {
        Ejecucion = ejecucion;
    }

    public Integer getIdOperarioEjecuta() {
        return IdOperarioEjecuta;
    }

    public void setIdOperarioEjecuta(Integer idOperarioEjecuta) {
        IdOperarioEjecuta = idOperarioEjecuta;
    }

    public Integer getIdOperarioPrograma() {
        return IdOperarioPrograma;
    }

    public void setIdOperarioPrograma(Integer idOperarioPrograma) {
        IdOperarioPrograma = idOperarioPrograma;
    }

    @NonNull
    @Override
    public String toString() {
        return  this.getPosicion()+" "+this.getCodigoOrden();
    }
}

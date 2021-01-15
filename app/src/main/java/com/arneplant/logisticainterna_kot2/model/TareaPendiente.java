package com.arneplant.logisticainterna_kot2.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class TareaPendiente implements Serializable {
    private int IdOrden;
    private int IdPedido;
    private String CodigoOrden;
    private String NombreCliente;
    private String Modelo;
    private String Utillaje;
    private String Descripcion;
    private String CodigoArticulo;
    private int Ciclo;
    private String TallaUtillaje;
    private String TallasArticulo;
    private float ParesTotales;
    private float ParesFabricar;
    private float ParesFabricarDivididos;
    private float ParesFabricados;
    private float ParesPendientes;
    private int IdOfotc;
    private int Posicion;
    private int IdMaquina;
    private int Agrupacion;
    private boolean Nrocesado;
    private boolean nuevo;

    public int getIdOrden() {
        return IdOrden;
    }

    public void setIdOrden(int idOrden) {
        IdOrden = idOrden;
    }

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int idPedido) {
        IdPedido = idPedido;
    }

    public String getCodigoOrden() {
        return CodigoOrden;
    }

    public void setCodigoOrden(String codigoOrden) {
        CodigoOrden = codigoOrden;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getUtillaje() {
        return Utillaje;
    }

    public void setUtillaje(String utillaje) {
        Utillaje = utillaje;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCodigoArticulo() {
        return CodigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        CodigoArticulo = codigoArticulo;
    }

    public int getCiclo() {
        return Ciclo;
    }

    public void setCiclo(int ciclo) {
        Ciclo = ciclo;
    }

    public String getTallaUtillaje() {
        return TallaUtillaje;
    }

    public void setTallaUtillaje(String tallaUtillaje) {
        TallaUtillaje = tallaUtillaje;
    }

    public String getTallasArticulo() {
        return TallasArticulo;
    }

    public void setTallasArticulo(String tallasArticulo) {
        TallasArticulo = tallasArticulo;
    }

    public float getParesTotales() {
        return ParesTotales;
    }

    public void setParesTotales(float paresTotales) {
        ParesTotales = paresTotales;
    }

    public float getParesFabricar() {
        return ParesFabricar;
    }

    public void setParesFabricar(float paresFabricar) {
        ParesFabricar = paresFabricar;
    }

    public float getParesFabricarDivididos() {
        return ParesFabricarDivididos;
    }

    public void setParesFabricarDivididos(float paresFabricarDivididos) {
        ParesFabricarDivididos = paresFabricarDivididos;
    }

    public float getParesFabricados() {
        return ParesFabricados;
    }

    public void setParesFabricados(float paresFabricados) {
        ParesFabricados = paresFabricados;
    }

    public float getParesPendientes() {
        return ParesPendientes;
    }

    public void setParesPendientes(float paresPendientes) {
        ParesPendientes = paresPendientes;
    }

    public int getIdOfotc() {
        return IdOfotc;
    }

    public void setIdOfotc(int idOfotc) {
        IdOfotc = idOfotc;
    }

    public int getPosicion() {
        return Posicion;
    }

    public void setPosicion(int posicion) {
        Posicion = posicion;
    }

    public int getIdMaquina() {
        return IdMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        IdMaquina = idMaquina;
    }

    public boolean isNrocesado() {
        return Nrocesado;
    }

    public void setNrocesado(boolean nrocesado) {
        Nrocesado = nrocesado;
    }

    public double getCantidadPendiente()
    {
        return this.getParesFabricar() - this.getParesFabricados();
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public int getAgrupacion() {
        return Agrupacion;
    }

    public void setAgrupacion(int agrupacion) {
        Agrupacion = agrupacion;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getPosicion()+ ". "+ this.getCodigoOrden()+ " - " + this.getUtillaje()+ " - " + this.getTallaUtillaje();
    }
}

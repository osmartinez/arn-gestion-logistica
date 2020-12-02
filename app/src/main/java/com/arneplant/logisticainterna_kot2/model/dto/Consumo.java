package com.arneplant.logisticainterna_kot2.model.dto;

import java.util.Date;
import java.util.Objects;

public class Consumo {
    private int IdOrden;
    private String NombreMaquina;
    private String CodigoMaquina;
    private int IdMaquina;
    private String CodigoOrden;
    private String Cliente;
    private String Modelo;
    private String CodPrepaquete;
    private String CodPrepaqueteAgrupacion = null;
    private String Talla;
    private String TallaUtillaje;
    private double CantidadPaquete;
    private double CantidadFabricar;
    private double CantidadFabricada;
    private double CantidadPendiente;
    private double CantidadPendienteAnterior;
    private int Estado;
    private String FechaConsumo;
    private int IdOperacion;
    private String CodSeccion;

    public int getIdOrden() {
        return IdOrden;
    }

    public void setIdOrden(int idOrden) {
        IdOrden = idOrden;
    }

    public String getNombreMaquina() {
        return NombreMaquina;
    }

    public void setNombreMaquina(String nombreMaquina) {
        NombreMaquina = nombreMaquina;
    }

    public String getCodigoMaquina() {
        return CodigoMaquina;
    }

    public void setCodigoMaquina(String codigoMaquina) {
        CodigoMaquina = codigoMaquina;
    }

    public int getIdMaquina() {
        return IdMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        IdMaquina = idMaquina;
    }

    public String getCodigoOrden() {
        return CodigoOrden;
    }

    public void setCodigoOrden(String codigoOrden) {
        CodigoOrden = codigoOrden;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getCodPrepaquete() {
        return CodPrepaquete;
    }

    public void setCodPrepaquete(String codPrepaquete) {
        CodPrepaquete = codPrepaquete;
    }

    public String getCodPrepaqueteAgrupacion() {
        return CodPrepaqueteAgrupacion;
    }

    public void setCodPrepaqueteAgrupacion(String codPrepaqueteAgrupacion) {
        CodPrepaqueteAgrupacion = codPrepaqueteAgrupacion;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String talla) {
        Talla = talla;
    }

    public String getTallaUtillaje() {
        return TallaUtillaje;
    }

    public void setTallaUtillaje(String tallaUtillaje) {
        TallaUtillaje = tallaUtillaje;
    }

    public double getCantidadPaquete() {
        return CantidadPaquete;
    }

    public void setCantidadPaquete(double cantidadPaquete) {
        CantidadPaquete = cantidadPaquete;
    }

    public double getCantidadFabricar() {
        return CantidadFabricar;
    }

    public void setCantidadFabricar(double cantidadFabricar) {
        CantidadFabricar = cantidadFabricar;
    }

    public double getCantidadFabricada() {
        return CantidadFabricada;
    }

    public void setCantidadFabricada(double cantidadFabricada) {
        CantidadFabricada = cantidadFabricada;
    }

    public double getCantidadPendiente() {
        return CantidadPendiente;
    }

    public void setCantidadPendiente(double cantidadPendiente) {
        CantidadPendiente = cantidadPendiente;
    }

    public double getCantidadPendienteAnterior() {
        return CantidadPendienteAnterior;
    }

    public void setCantidadPendienteAnterior(double cantidadPendienteAnterior) {
        CantidadPendienteAnterior = cantidadPendienteAnterior;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public String getFechaConsumo() {
        return FechaConsumo;
    }

    public void setFechaConsumo(String fechaConsumo) {
        FechaConsumo = fechaConsumo;
    }

    public int getIdOperacion() {
        return IdOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        IdOperacion = idOperacion;
    }

    public String getCodSeccion() {
        return CodSeccion;
    }

    public void setCodSeccion(String codSeccion) {
        CodSeccion = codSeccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumo consumo = (Consumo) o;
        return IdOrden == consumo.IdOrden &&
                IdOperacion == consumo.IdOperacion &&
                CodPrepaquete.equals(consumo.CodPrepaquete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdOrden, CodPrepaquete, IdOperacion);
    }
}


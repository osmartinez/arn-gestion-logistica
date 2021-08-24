package com.arneplant.logisticainterna_kot2.model;

import java.util.Date;

public class LaborOperario {
    private int IdLaborOperario;
    private int IdOperario;
    private int IdLabor;
    private Date FechaInicioLabor;
    private Date FechaFinLabor;
    private String Clave;
    private String Descripcion;
    private String NombreControl;
    private double Valor;
    private double Normal;
    private double Optimo;
    private Integer IdPuesto;
    private Integer IdMaquina;

    public int getIdLaborOperario() {
        return IdLaborOperario;
    }

    public void setIdLaborOperario(int idLaborOperario) {
        IdLaborOperario = idLaborOperario;
    }

    public int getIdOperario() {
        return IdOperario;
    }

    public void setIdOperario(int idOperario) {
        IdOperario = idOperario;
    }

    public int getIdLabor() {
        return IdLabor;
    }

    public void setIdLabor(int idLabor) {
        IdLabor = idLabor;
    }

    public Date getFechaInicioLabor() {
        return FechaInicioLabor;
    }

    public void setFechaInicioLabor(Date fechaInicioLabor) {
        FechaInicioLabor = fechaInicioLabor;
    }

    public Date getFechaFinLabor() {
        return FechaFinLabor;
    }

    public void setFechaFinLabor(Date fechaFinLabor) {
        FechaFinLabor = fechaFinLabor;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getNombreControl() {
        return NombreControl;
    }

    public void setNombreControl(String nombreControl) {
        NombreControl = nombreControl;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

    public double getNormal() {
        return Normal;
    }

    public void setNormal(double normal) {
        Normal = normal;
    }

    public double getOptimo() {
        return Optimo;
    }

    public void setOptimo(double optimo) {
        Optimo = optimo;
    }

    public Integer getIdPuesto() {
        return IdPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        IdPuesto = idPuesto;
    }

    public Integer getIdMaquina() {
        return IdMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        IdMaquina = idMaquina;
    }
}

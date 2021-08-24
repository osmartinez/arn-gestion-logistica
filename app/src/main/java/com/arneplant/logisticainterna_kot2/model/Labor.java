package com.arneplant.logisticainterna_kot2.model;

public class Labor {
    private int Id;
    private String NombreControl;
    private String Clave;
    private String Descripcion;
    private double Valor;
    private double Normal;
    private double Optimo;
    private String CodigoEtiqueta;

    public Labor() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombreControl() {
        return NombreControl;
    }

    public void setNombreControl(String nombreControl) {
        NombreControl = nombreControl;
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

    public String getCodigoEtiqueta() {
        return CodigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        CodigoEtiqueta = codigoEtiqueta;
    }
}

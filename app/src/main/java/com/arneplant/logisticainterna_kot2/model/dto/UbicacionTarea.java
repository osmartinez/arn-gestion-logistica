package com.arneplant.logisticainterna_kot2.model.dto;

public class UbicacionTarea {
    private int NumCajas;
    private String Talla;
    private double Cantidad;
    private String CodUbicacion;
    private String Descripcion;
    private boolean EsMaquina;
    private boolean Ejecucion;
    private double CantidadFabricar;
    private double CantidadPendiente;

    public int getNumCajas() {
        return NumCajas;
    }

    public void setNumCajas(int numCajas) {
        NumCajas = numCajas;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String talla) {
        Talla = talla;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double cantidad) {
        Cantidad = cantidad;
    }

    public String getCodUbicacion() {
        return CodUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        CodUbicacion = codUbicacion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public boolean isEsMaquina() {
        return EsMaquina;
    }

    public void setEsMaquina(boolean esMaquina) {
        EsMaquina = esMaquina;
    }

    public boolean isEjecucion() {
        return Ejecucion;
    }

    public void setEjecucion(boolean ejecucion) {
        Ejecucion = ejecucion;
    }

    public double getCantidadFabricar() {
        return CantidadFabricar;
    }

    public void setCantidadFabricar(double cantidadFabricar) {
        CantidadFabricar = cantidadFabricar;
    }

    public double getCantidadPendiente() {
        return CantidadPendiente;
    }

    public void setCantidadPendiente(double cantidadPendiente) {
        CantidadPendiente = cantidadPendiente;
    }
}

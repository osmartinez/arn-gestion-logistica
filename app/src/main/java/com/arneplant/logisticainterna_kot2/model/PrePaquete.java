package com.arneplant.logisticainterna_kot2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PrePaquete implements Serializable {
    private String codigoEtiqueta;
    private OrdenFabricacion ordenFabricacion;
    private String talla;
    private Double cantidad;
    private Ubicacion ubicacion;
    private String codUbicacion;
    private Date fechaAsociacion;
    private List<PrepaqueteConsumido> consumos;
    private PrePaqueteAgrupacion agrupacion;
    private Integer idAgrupacionMaquina;
    private String codAgrupacion;


    private EspacioVagon espacioVagon;

    public EspacioVagon getEspacioVagon() {
        return espacioVagon;
    }

    public void setEspacioVagon(EspacioVagon espacioVagon) {
        this.espacioVagon = espacioVagon;
    }

    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public OrdenFabricacion getOrdenFabricacion() {
        return ordenFabricacion;
    }

    public void setOrdenFabricacion(OrdenFabricacion ordenFabricacion) {
        this.ordenFabricacion= ordenFabricacion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCodUbicacion() {
        return codUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        this.codUbicacion = codUbicacion;
    }

    public Date getFechaAsociacion() {
        return fechaAsociacion;
    }

    public void setFechaAsociacion(Date fechaAsociacion) {
        this.fechaAsociacion = fechaAsociacion;
    }

    public List<PrepaqueteConsumido> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<PrepaqueteConsumido> consumos) {
        this.consumos = consumos;
    }


    public PrePaqueteAgrupacion getAgrupacion() {
        return agrupacion;
    }

    public void setAgrupacion(PrePaqueteAgrupacion agrupacion) {
        this.agrupacion = agrupacion;
    }



    public String getCodAgrupacion() {
        return codAgrupacion;
    }

    public void setCodAgrupacion(String codAgrupacion) {
        this.codAgrupacion = codAgrupacion;
    }

    public Integer getIdAgrupacionMaquina() {
        return idAgrupacionMaquina;
    }

    public void setIdAgrupacionMaquina(Integer idAgrupacionMaquina) {
        this.idAgrupacionMaquina = idAgrupacionMaquina;
    }

    @Override
    public String toString() {
        return "{"+this.codigoEtiqueta+", "+this.talla+", "+this.cantidad+"}";
    }


}
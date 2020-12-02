package com.arneplant.logisticainterna_kot2.model;

import java.io.Serializable;
import java.util.List;

public class OrdenFabricacion implements Serializable {
    private Integer id;
    private Articulo articulo;
    private String codigoArticulo;
    private String codigo;
    private List<OrdenFabricacionOperacion> ordenFabOperaciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public List<OrdenFabricacionOperacion> getOrdenFabOperaciones() {
        return ordenFabOperaciones;
    }

    public void setOrdenFabOperaciones(List<OrdenFabricacionOperacion> ordenFabOperaciones) {
        this.ordenFabOperaciones = ordenFabOperaciones;
    }

}

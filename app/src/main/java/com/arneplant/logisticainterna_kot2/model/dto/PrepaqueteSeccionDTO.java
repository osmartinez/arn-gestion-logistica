package com.arneplant.logisticainterna_kot2.model.dto;

import java.io.Serializable;

public class PrepaqueteSeccionDTO implements Serializable {
    private String Codigo;
    private String CodigoArticulo;
    private String DESCRIPCIONARTICULO;
    private String NOMBRECLI;
    private String CodigoAgrupacion;
    private String CodigoEtiqueta;
    private String Talla;
    private double Cantidad;
    private String CodUtillaje;
    private String Descripcion;
    private String IdUtillajeTalla;
    private String Tallas;
    private double CantidadFabricar;
    private double CantidadFabricada;
    private String PedidoLinea;
    private int IdTarea;
    private int IdOperacion;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getCodigoArticulo() {
        return CodigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        CodigoArticulo = codigoArticulo;
    }

    public String getDESCRIPCIONARTICULO() {
        return DESCRIPCIONARTICULO;
    }

    public void setDESCRIPCIONARTICULO(String DESCRIPCIONARTICULO) {
        this.DESCRIPCIONARTICULO = DESCRIPCIONARTICULO;
    }

    public String getNOMBRECLI() {
        return NOMBRECLI;
    }

    public void setNOMBRECLI(String NOMBRECLI) {
        this.NOMBRECLI = NOMBRECLI;
    }

    public String getCodigoAgrupacion() {
        return CodigoAgrupacion;
    }

    public void setCodigoAgrupacion(String codigoAgrupacion) {
        CodigoAgrupacion = codigoAgrupacion;
    }

    public String getCodigoEtiqueta() {
        return CodigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        CodigoEtiqueta = codigoEtiqueta;
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

    public String getCodUtillaje() {
        return CodUtillaje;
    }

    public void setCodUtillaje(String codUtillaje) {
        CodUtillaje = codUtillaje;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getIdUtillajeTalla() {
        return IdUtillajeTalla;
    }

    public void setIdUtillajeTalla(String idUtillajeTalla) {
        IdUtillajeTalla = idUtillajeTalla;
    }

    public String getTallas() {
        return Tallas;
    }

    public void setTallas(String tallas) {
        Tallas = tallas;
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

    public String getPedidoLinea() {
        return PedidoLinea;
    }

    public void setPedidoLinea(String pedidoLinea) {
        PedidoLinea = pedidoLinea;
    }

    public int getIdTarea() {
        return IdTarea;
    }

    public void setIdTarea(int idTarea) {
        IdTarea = idTarea;
    }

    public int getIdOperacion() {
        return IdOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        IdOperacion = idOperacion;
    }


}

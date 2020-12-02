package com.arneplant.logisticainterna_kot2.model.dto;

import com.arneplant.logisticainterna_kot2.model.OrdenFabricacion;
import com.arneplant.logisticainterna_kot2.model.PrePaquete;
import com.arneplant.logisticainterna_kot2.model.Ubicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrePaqueteDTO implements Serializable {

    private String codigoEtiqueta;
    private String talla;
    private Double cantidad;
    private Ubicacion ubicacion;
    private Date fechaAsociacion;
    private String codigoOrdenFabricacion;
    private String cliente;
    private String codigoArticulo;
    private boolean agrupacion;
    private List<OrdenFabricacionDTO> ordenesFabricacion;
    private List<PrePaqueteConsumidoDTO> consumos;
    private List<RelacionPrepaqueteConsumo> relacionesConsumos;
    private Integer idAgrupacionMaquina;

    private int idOperacionConsumido;

    public PrePaqueteDTO() {

    }


    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }
    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public String getTalla() {
        return talla;
    }
    public void setTalla(String talla) {
        this.talla = talla;
    }
    public Double getCantidad() {
        return cantidad;
    }
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    public Date getFechaAsociacion() {
        return fechaAsociacion;
    }
    public void setFechaAsociacion(Date fechaAsociacion) {
        this.fechaAsociacion = fechaAsociacion;
    }

    public String getCodigoOrdenFabricacion() {
        return codigoOrdenFabricacion;
    }

    public void setCodigoOrdenFabricacion(String codigoOrdenFabricacion) {
        this.codigoOrdenFabricacion = codigoOrdenFabricacion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public boolean isAgrupacion() {
        return agrupacion;
    }

    public void setAgrupacion(boolean agrupacion) {
        this.agrupacion = agrupacion;
    }

    public List<OrdenFabricacionDTO> getOrdenesFabricacion() {
        return ordenesFabricacion;
    }

    public void setOrdenesFabricacion(List<OrdenFabricacionDTO> ordenesFabricacion) {
        this.ordenesFabricacion = ordenesFabricacion;
    }

    public List<PrePaqueteConsumidoDTO> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<PrePaqueteConsumidoDTO> consumos) {
        this.consumos = consumos;
    }

    public int getIdOperacionConsumido() {
        return idOperacionConsumido;
    }

    public void setIdOperacionConsumido(int idOperacionConsumido) {
        this.idOperacionConsumido = idOperacionConsumido;
    }

    public List<RelacionPrepaqueteConsumo> getRelacionesConsumos() {
        return relacionesConsumos;
    }

    public void setRelacionesConsumos(List<RelacionPrepaqueteConsumo> relacionesConsumos) {
        this.relacionesConsumos = relacionesConsumos;
    }

    public Integer getIdAgrupacionMaquina() {
        return idAgrupacionMaquina;
    }

    public void setIdAgrupacionMaquina(Integer idAgrupacionMaquina) {
        this.idAgrupacionMaquina = idAgrupacionMaquina;
    }

    public PrePaquete toPrepaquete(){
        PrePaquete pre = new PrePaquete();
        OrdenFabricacion orden = new OrdenFabricacion();
        orden.setCodigo(this.codigoOrdenFabricacion);
        orden.setCodigoArticulo(this.codigoArticulo);
        pre.setCantidad(this.cantidad);
        pre.setOrdenFabricacion(orden);
        pre.setTalla(this.talla);
        pre.setUbicacion(this.ubicacion);
        pre.setCodigoEtiqueta(this.codigoEtiqueta);
        return pre;
    }

    public void addRelacionConsumo(RelacionPrepaqueteConsumo rpc) {
        if(this.relacionesConsumos==null) {
            this.relacionesConsumos = new ArrayList<RelacionPrepaqueteConsumo>();
        }
        this.relacionesConsumos.add(rpc);
    }


}
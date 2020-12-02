package com.arneplant.logisticainterna_kot2.model;

import com.arneplant.logisticainterna_kot2.model.dto.Consumo;

import java.io.Serializable;
import java.util.List;

public class Respuesta implements Serializable {
    private short codigo;
    private String mensaje;
    private List<OrdenFabricacionOperacionTallaCantidad> ofotcs;
    private List<Consumo> consumos;
    private List<TareaPendiente> tareasPendientes;

    public List<TareaPendiente> getTareasPendientes() {
        return tareasPendientes;
    }

    public void setTareasPendientes(List<TareaPendiente> tareasPendientes) {
        this.tareasPendientes = tareasPendientes;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(short codigo) {
        this.codigo = codigo;
    }
    public String getMensaje() {
        return mensaje;
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    private String getMensaje(short codigo) {
        String msg = "";
        switch(codigo) {
            case CodigoRespuesta.OK:
                msg = "OK";
                break;
            case CodigoRespuesta.ERROR_CONEXION:
                msg = "Error de conexion";
                break;
            case CodigoRespuesta.NO_EXISTE:
                msg = "No existe";
                break;

            case CodigoRespuesta.EXCEPCION:
                msg = "Ocurrió una excepción";
                break;
            case CodigoRespuesta.CULOTES_CUADROS:
                break;
            case CodigoRespuesta.MULTI_OPERACION:
                break;
            default:
                msg ="Error sin definir";
                break;
        }
        return msg;
    }

    public Respuesta(short codigo) {
        super();
        this.codigo = codigo;
        this.mensaje = this.getMensaje(codigo);
    }

    public Respuesta(short codigo, String msg) {
        super();
        this.codigo = codigo;
        this.mensaje = msg;
    }

    public Respuesta(String msg) {
        super();
        this.mensaje = msg;
        this.codigo = (short)400;
    }

    public List<OrdenFabricacionOperacionTallaCantidad> getOfotcs() {
        return ofotcs;
    }

    public void setOfotcs(List<OrdenFabricacionOperacionTallaCantidad> ofotcs) {
        this.ofotcs = ofotcs;
    }
}

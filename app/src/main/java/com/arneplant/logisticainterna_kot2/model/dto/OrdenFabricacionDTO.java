package com.arneplant.logisticainterna_kot2.model.dto;

import java.io.Serializable;
import java.util.List;

public class OrdenFabricacionDTO implements Serializable {
    private int id;
    private String codigo;
    private String codigoArticulo;
    private List<OrdenFabricacionOperacionDTO> operaciones;

    public OrdenFabricacionDTO() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getCodigoArticulo() {
        return codigoArticulo;
    }
    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }
    public List<OrdenFabricacionOperacionDTO> getOperaciones() {
        return operaciones;
    }
    public void setOperaciones(List<OrdenFabricacionOperacionDTO> operaciones) {
        this.operaciones = operaciones;
    }


}

package com.arneplant.logisticainterna_kot2.model.dto;

public class MaquinaEtiqueta {
    public String codigoMaquina;
    public String codigoEtiqueta;
    public Integer idMaquina;
    public Integer idOperacionConsumir;
    public Integer idOperario;

    public MaquinaEtiqueta(String codigoMaquina, String codigoEtiqueta, Integer idOperacionConsumir, Integer idOperario) {
        this.codigoMaquina = codigoMaquina;
        this.codigoEtiqueta = codigoEtiqueta;
        this.idOperacionConsumir = idOperacionConsumir;
        this.idOperario = idOperario;
    }

    public MaquinaEtiqueta(String codigoEtiqueta, Integer idMaquina) {
        this.codigoEtiqueta = codigoEtiqueta;
        this.idMaquina = idMaquina;
    }

    public MaquinaEtiqueta() {
    }

    public String getCodigoMaquina() {
        return codigoMaquina;
    }

    public void setCodigoMaquina(String codigoMaquina) {
        this.codigoMaquina = codigoMaquina;
    }

    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        this.codigoEtiqueta = codigoEtiqueta;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Integer getIdOperacionConsumir() {
        return idOperacionConsumir;
    }

    public void setIdOperacionConsumir(Integer idOperacionConsumir) {
        this.idOperacionConsumir = idOperacionConsumir;
    }

    public Integer getIdOperario() {
        return idOperario;
    }

    public void setIdOperario(Integer idOperario) {
        this.idOperario = idOperario;
    }
}

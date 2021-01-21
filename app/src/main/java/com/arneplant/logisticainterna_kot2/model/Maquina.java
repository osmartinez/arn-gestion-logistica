package com.arneplant.logisticainterna_kot2.model;

import androidx.annotation.Nullable;

import com.arneplant.logisticainterna_kot2.model.dto.Consumo;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Maquina implements Serializable {
    private int ID;
    private String Nombre;
    private String Descripcion = null;
    private String CodigoAgrupacion = null;
    private String CodSeccion;
    private String IDPlantillaCalendario = null;
    private String PlantillaCalendarioDesde = null;
    private String Averia = null;
    private String IdIncidencia = null;
    private String IncluirTiempoPreparacion = null;
    private String IdTaller = null;
    private String TallerHorasCapacidadDiaria = null;
    private String FechaBorrado = null;
    private String UsuarioBorrado = null;
    private String CorrectorCapacidad = null;
    private String CodUbicacion;
    private String CodigoEtiqueta;
    private Integer IdBancada;
    private List<Consumo> consumos;
    private boolean seleccionada;
    private String IpAutomata = null;
    private int Posicion = 0;

    public Maquina() {
        this.consumos = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCodigoAgrupacion() {
        return CodigoAgrupacion;
    }

    public void setCodigoAgrupacion(String codigoAgrupacion) {
        CodigoAgrupacion = codigoAgrupacion;
    }

    public String getCodSeccion() {
        return CodSeccion;
    }

    public void setCodSeccion(String codSeccion) {
        CodSeccion = codSeccion;
    }

    public String getIDPlantillaCalendario() {
        return IDPlantillaCalendario;
    }

    public void setIDPlantillaCalendario(String IDPlantillaCalendario) {
        this.IDPlantillaCalendario = IDPlantillaCalendario;
    }

    public String getPlantillaCalendarioDesde() {
        return PlantillaCalendarioDesde;
    }

    public void setPlantillaCalendarioDesde(String plantillaCalendarioDesde) {
        PlantillaCalendarioDesde = plantillaCalendarioDesde;
    }

    public String getAveria() {
        return Averia;
    }

    public void setAveria(String averia) {
        Averia = averia;
    }

    public String getIdIncidencia() {
        return IdIncidencia;
    }

    public void setIdIncidencia(String idIncidencia) {
        IdIncidencia = idIncidencia;
    }

    public String getIncluirTiempoPreparacion() {
        return IncluirTiempoPreparacion;
    }

    public void setIncluirTiempoPreparacion(String incluirTiempoPreparacion) {
        IncluirTiempoPreparacion = incluirTiempoPreparacion;
    }

    public String getIdTaller() {
        return IdTaller;
    }

    public void setIdTaller(String idTaller) {
        IdTaller = idTaller;
    }

    public String getTallerHorasCapacidadDiaria() {
        return TallerHorasCapacidadDiaria;
    }

    public void setTallerHorasCapacidadDiaria(String tallerHorasCapacidadDiaria) {
        TallerHorasCapacidadDiaria = tallerHorasCapacidadDiaria;
    }

    public String getFechaBorrado() {
        return FechaBorrado;
    }

    public void setFechaBorrado(String fechaBorrado) {
        FechaBorrado = fechaBorrado;
    }

    public String getUsuarioBorrado() {
        return UsuarioBorrado;
    }

    public void setUsuarioBorrado(String usuarioBorrado) {
        UsuarioBorrado = usuarioBorrado;
    }

    public String getCorrectorCapacidad() {
        return CorrectorCapacidad;
    }

    public void setCorrectorCapacidad(String correctorCapacidad) {
        CorrectorCapacidad = correctorCapacidad;
    }

    public String getCodUbicacion() {
        return CodUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        CodUbicacion = codUbicacion;
    }

    public String getCodigoEtiqueta() {
        return CodigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        CodigoEtiqueta = codigoEtiqueta;
    }

    public Integer getIdBancada() {
        return IdBancada;
    }

    public void setIdBancada(Integer idBancada) {
        IdBancada = idBancada;
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    public String getIpAutomata() {
        return IpAutomata;
    }

    public void setIpAutomata(String ipAutomata) {
        IpAutomata = ipAutomata;
    }

    public int getPosicion() {
        return Posicion;
    }

    public void setPosicion(int posicion) {
        Posicion = posicion;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Maquina) {
            Maquina o = (Maquina) obj;
            return o.getID() == (this.getID());
        }
        return false;
    }
}

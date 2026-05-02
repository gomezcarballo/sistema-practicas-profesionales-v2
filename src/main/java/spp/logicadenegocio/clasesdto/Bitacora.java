/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author gomes
 */
public class Bitacora {
    
    private int idBitacora;
    private String actividadRealizada;
    private String seccionUtilizada;
    private String detalle;
    private LocalDate fecha;
    private Usuario usuario;

    public Bitacora() {
    }

    public Bitacora(int idBitacora, String actividadRealizada, String seccionUtilizada, String detalle, LocalDate fecha, Usuario usuario) {
        this.idBitacora = idBitacora;
        this.actividadRealizada = actividadRealizada;
        this.seccionUtilizada = seccionUtilizada;
        this.detalle = detalle;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }

    public String getActividadRealizada() {
        return actividadRealizada;
    }

    public void setActividadRealizada(String actividadRealizada) {
        this.actividadRealizada = actividadRealizada;
    }

    public String getSeccionUtilizada() {
        return seccionUtilizada;
    }

    public void setSeccionUtilizada(String seccionUtilizada) {
        this.seccionUtilizada = seccionUtilizada;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }    
    
}

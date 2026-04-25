/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Luz Fernanda H J
 */
public class Actividad {
    
    private int idActividad;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaLimite;
    private Profesor profesor;
    
    public Actividad(){
        
    }
    
    public Actividad(int idActividad, String titulo, String descripcion, 
            LocalDateTime fechaLimite, Profesor profesor){
        
        this.idActividad = idActividad; 
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.profesor = profesor;
        
    }
    
    public int getIdActividad(){
        return idActividad; 
    } 
    
    public void setIdActividad(int idActividad){
        this.idActividad = idActividad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idActividad;
        hash = 53 * hash + Objects.hashCode(this.titulo);
        hash = 53 * hash + Objects.hashCode(this.descripcion);
        hash = 53 * hash + Objects.hashCode(this.fechaLimite);
        hash = 53 * hash + Objects.hashCode(this.profesor);
        return hash;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (objeto == null) {
            return false;
        }
        if (getClass() != objeto.getClass()) {
            return false;
        }
        final Actividad actividadReferencia = (Actividad) objeto;
        if (this.idActividad != actividadReferencia.idActividad) {
            return false;
        }
        if (!Objects.equals(this.titulo, actividadReferencia.titulo)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, actividadReferencia.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.fechaLimite, actividadReferencia.fechaLimite)) {
            return false;
        }
        return Objects.equals(this.profesor, actividadReferencia.profesor);
    }
       
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

import java.time.LocalDateTime;

/**
 *
 * @author Luz Fernanda H J
 */
public class Actividad {
    
    private int idActividad;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaLimite;
    private int idProfesor;
    
    public Actividad(){
        
    }
    
    public Actividad(int idActividad, String titulo, String descripcion, 
            LocalDateTime fechaLimite, int idProfesor){
        
        this.idActividad = idActividad; 
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.idProfesor = idProfesor;
        
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

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }       
    
}

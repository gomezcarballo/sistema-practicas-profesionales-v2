/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Luz Fernanda H J
 */
public class Proyecto {
    
    private int idProyecto; 
    private String nombre; 
    private String descripcion;
    private String nombreResponsable;
    private int cupoMaximo;
    private boolean esActivo;
    private BooleanProperty esSeleccionado = new SimpleBooleanProperty(false);
    private Organizacion Organizacion;

    public Proyecto (){
    }
    
    public Proyecto(int idProyecto, String nombre, String descripcion, String nombreResponsable,
           int cupoMaximo, boolean esActivo, Organizacion Organizacion) {
        
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nombreResponsable = nombreResponsable;
        this.cupoMaximo = cupoMaximo;
        this.esActivo = esActivo;
        this.Organizacion = Organizacion;
        
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(boolean esActivo) {
        this.esActivo = esActivo;
    }
    
    public boolean getEsSeleccionado(){
        return esSeleccionado.get();
    }
    
    public void setEsSeleccionado(boolean esSeleccionado){
        this.esSeleccionado.set(esSeleccionado);
    }
    
    public BooleanProperty  propiedadEsSeleccionado() {
        return esSeleccionado;
    }

    public Organizacion getOrganizacion() {
        return Organizacion;
    }

    public void setOrganizacion(Organizacion Organizacion) {
        this.Organizacion = Organizacion;
    }
    
}

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
    private String objetivoGeneral;
    private String nombreResponsable;
    private String contactoResponsable;
    private String metodologia;
    private int cupoMaximo;
    private boolean esActivo;
    private BooleanProperty esSeleccionado = new SimpleBooleanProperty(false);
    private Organizacion organizacion;

    public Proyecto (){
    }
    
    public Proyecto(int idProyecto, String nombre, String objetivoGeneral, String nombreResponsable,
           int cupoMaximo, String contactoResponsable, String metodologia, boolean esActivo, Organizacion Organizacion) {
        
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.objetivoGeneral = objetivoGeneral;
        this.nombreResponsable = nombreResponsable;
        this.contactoResponsable = contactoResponsable;
        this.metodologia = metodologia;
        this.cupoMaximo = cupoMaximo;
        this.esActivo = esActivo;
        this.organizacion = Organizacion;
        
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

    public String getObjetivoGeneral() {
        return objetivoGeneral;
    }

    public void setObjetivoGeneral(String objetivoGeneral) {
        this.objetivoGeneral = objetivoGeneral;
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
        return organizacion;
    }

    public void setOrganizacion(Organizacion Organizacion) {
        this.organizacion = Organizacion;
    }
    
    public String getNombreOrganizacion() {

        return organizacion.getNombre();

    }

    public String getContactoResponsable() {
        return contactoResponsable;
    }

    public void setContactoResponsable(String contactoResponsable) {
        this.contactoResponsable = contactoResponsable;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }
    
    
    
}

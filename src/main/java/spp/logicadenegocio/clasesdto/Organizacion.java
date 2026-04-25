/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author Luz Fernanda H J
 */
public class Organizacion {
    
    private int idOrganizacion;
    private String nombre; 
    private String sector;
    private String direccion; 
    private boolean esActivo; 
    
    public Organizacion() {
    }
   
    public Organizacion(int idOrganizacion, String nombre, String sector, 
           String direccion, boolean esActivo) {
        
        this.idOrganizacion = idOrganizacion;
        this.nombre = nombre;
        this.sector = sector;
        this.direccion = direccion;
        this.esActivo = esActivo;
        
    }

    public int getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(int idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(boolean esActivo) {
        this.esActivo = esActivo;
    }
    
}

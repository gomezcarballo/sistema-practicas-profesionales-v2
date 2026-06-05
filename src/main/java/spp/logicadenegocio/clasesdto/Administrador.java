/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author gomes
 */
public class Administrador extends Usuario {
    
    private String numeroDePersonal;

    public Administrador() {
    }
    
    public Administrador (int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, 
            String correoInstitucional, String contraseña, boolean esActivo, String numeroDePersonal){
        
        super(idUsuario, nombre, apellidoPaterno, apellidoMaterno, correoInstitucional, contraseña, esActivo);
        this.numeroDePersonal = numeroDePersonal;
        
    }
    
    public String getNumeroDePersonal(){
        return numeroDePersonal;
    }
    
    public void setNumeroDePersonal(String numeroDePersonal){
        this.numeroDePersonal = numeroDePersonal;
    }
    
}

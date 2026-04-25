/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author Luz Fernanda H J
 */
public class Coordinador extends Usuario{
    
    private String numeroDePersonal;
    
    public Coordinador(){
        
    }
    
    public Coordinador (int idUsuario, String nombre, String apellidos, String contraseña,
           boolean esActivo, String numeroDePersonal){
        
        super(idUsuario,nombre,apellidos,contraseña,esActivo);
        this.numeroDePersonal = numeroDePersonal;
        
    }
    
    public String getNumeroDePersonal(){
        return numeroDePersonal;
    }
    
    public void setNumeroDePersonal(String numeroDePersonal){
        this.numeroDePersonal = numeroDePersonal;
    }
    
}

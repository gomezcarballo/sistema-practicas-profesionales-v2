/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author gomes
 */
public class SesionUsuario {
    
    private static SesionUsuario instancia;
    
    private int idUsuario;
    private String rol;
    private String identificador;
    private String hashContrasena;
    
    private SesionUsuario(){
    }
    
    public static SesionUsuario getInstancia() {
        
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
        
    }

    public void iniciarSesion(int idUsuario, String rol, String identificador, String hashContrasena) {
        
        this.idUsuario = idUsuario;
        this.rol = rol;
        this.identificador = identificador;
        this.hashContrasena = hashContrasena;
        
    }

    public void cerrarSesion() {
        instancia = null;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getRol() {
        return rol;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setHashContrasena(String hashContrasena) {
        this.hashContrasena = hashContrasena;
    }
 
    public String getHashContrasena() {
        return hashContrasena;
    }   
    
}

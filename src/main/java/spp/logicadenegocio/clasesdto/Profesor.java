/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author Luz Fernanda H J
 */
public class Profesor extends Usuario{
    
    private String numeroDePersonal;
    private String nrcAsignado;

    public Profesor() {
    }

    public Profesor(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno,
           String correoInstitucional, String contraseña, boolean esActivo,String numeroDePersonal, String nrcAsignado) {
        
        super(idUsuario, nombre, apellidoPaterno, apellidoMaterno, correoInstitucional, contraseña, esActivo);
        this.numeroDePersonal = numeroDePersonal;
        
    }
    
    public String getNumeroDePersonal() {
        return numeroDePersonal;
    }

    public void setNumeroDePersonal(String numeroDePersonal) {
        this.numeroDePersonal = numeroDePersonal;
    }

    public String getNrcAsignado() {
        return nrcAsignado;
    }

    public void setNrcAsignado(String nrcAsignado) {
        this.nrcAsignado = nrcAsignado;
    }
    
}

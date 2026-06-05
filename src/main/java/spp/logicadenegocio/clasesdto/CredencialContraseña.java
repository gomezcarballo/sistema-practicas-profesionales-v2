/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author gomes
 */
public class CredencialContraseña {
    
    private String contraseñaActual;
    private String contraseñaNueva;
    private String contraseñaConfirmada;

    public CredencialContraseña() {
    }

    public CredencialContraseña(String contraseñaActual, String contraseñaNueva, String contraseñaConfirmada) {
        this.contraseñaActual = contraseñaActual;
        this.contraseñaNueva = contraseñaNueva;
        this.contraseñaConfirmada = contraseñaConfirmada;
    }

    public String getContraseñaActual() {
        return contraseñaActual;
    }

    public void setContraseñaActual(String contraseñaActual) {
        this.contraseñaActual = contraseñaActual;
    }

    public String getContraseñaNueva() {
        return contraseñaNueva;
    }

    public void setContraseñaNueva(String contraseñaNueva) {
        this.contraseñaNueva = contraseñaNueva;
    }

    public String getContraseñaConfirmada() {
        return contraseñaConfirmada;
    }

    public void setContraseñaConfirmada(String contraseñaConfirmada) {
        this.contraseñaConfirmada = contraseñaConfirmada;
    }
    
    
    
}

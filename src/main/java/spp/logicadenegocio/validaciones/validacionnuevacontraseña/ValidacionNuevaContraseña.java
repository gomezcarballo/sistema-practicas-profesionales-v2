/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionnuevacontraseña;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.CredencialContraseña;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.contrasenas.hasheodecontrasenas.HasheoContrasena;

/**
 *
 * @author gomes
 */
public class ValidacionNuevaContraseña {
    

    private final int LONGITUD_MINIMA_CONTRASENA = 10;

    public List<String> validarCambioContraseña(CredencialContraseña credenciales) {
 
        List<String> listaErrores = new ArrayList<>();

        if (!esContraseñaActualValida(credenciales)) {
            
            listaErrores.add("La contraseña actual no es correcta.");
            
        } else if (!coincidenContraseñasNuevas(credenciales)) {
            
            listaErrores.add("Las contraseñas nuevas no coinciden.");
            
        } else if (!esLongitudNuevaContraseñaValida(credenciales)) {
            
            listaErrores.add("La nueva contraseña debe tener al menos " + LONGITUD_MINIMA_CONTRASENA + " caracteres.");
            
        }
        
        return listaErrores;

    }

    private boolean esContraseñaActualValida(CredencialContraseña credenciales) {
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        
        return HasheoContrasena.esContraseñaValida(credenciales.getContraseñaActual(),sesionUsuario.getHashContrasena());

    }

    private boolean coincidenContraseñasNuevas(CredencialContraseña credenciales) {

        return credenciales.getContraseñaNueva().equals(credenciales.getContraseñaConfirmada());

    }

    private boolean esLongitudNuevaContraseñaValida(CredencialContraseña credenciales) {

        return credenciales.getContraseñaNueva().length() >= LONGITUD_MINIMA_CONTRASENA;

    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesiniciosesion;

import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.utilerias.contrasenas.hasheodecontrasenas.HasheoContrasena;

/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionInicioDeSesion {
    
    public boolean validarContraseña(String contraseñaIngresada, UsuarioEncontrado usuario){
        
        String contraseñaRegistrada = usuario.getHashUsuarioEncontrado();
        boolean esContraseñaCorrecta = HasheoContrasena.esContraseñaValida(contraseñaIngresada,
        contraseñaRegistrada);
        
        return esContraseñaCorrecta;
    }
    
    public boolean sonFormatosValidos( String identificador ) {
        
        boolean sonCamposValidos = false;
        String patronCorreoElectronico = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)"
            + "*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)";
        
        String patronMatricula = "^[sS][0-9]{8}$";
        
        if( identificador.matches(patronMatricula) || identificador.matches( patronCorreoElectronico ) ){
            sonCamposValidos = true;
        }

        return sonCamposValidos;
        
    }
    
}

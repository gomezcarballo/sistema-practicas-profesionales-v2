/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesiniciosesion;

import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.hasheodecontrasenas.HasheoContrasena;


/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionInicioDeSesion {
    
    public void validarContraseña(String contraseñaIngresada, UsuarioEncontrado usuario)throws ReglaDeNegocioExcepcion{
        
        boolean esContraseñaCorrecta = HasheoContrasena.verificarContraseña(contraseñaIngresada,
        usuario.getHashUsuarioEncontrado());
            
        if (!esContraseñaCorrecta) {
            throw new ReglaDeNegocioExcepcion("La contraseña no es correcta");
        }
    }
    
    public void sonCamposValidosPorReglaNegocio( String identificador ) throws ReglaDeNegocioExcepcion {
        
        String PATRON_CORREO_ELECTRONICO = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)"
            + "*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)";
        
        if( !identificador.matches( "^[sS][0-9]{8}$" ) && !identificador.matches( PATRON_CORREO_ELECTRONICO ) ){
            throw new ReglaDeNegocioExcepcion("Identificador no valido. "
            + "Ingresa una matricula o correo institucional");
        }
        
    }
    
}

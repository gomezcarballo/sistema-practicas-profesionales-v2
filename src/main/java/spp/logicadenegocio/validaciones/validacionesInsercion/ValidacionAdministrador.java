/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;


import spp.logicadenegocio.clasesdto.Administrador;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionAdministrador {

    public void sonCamposValidosPorReglaNegocio(Administrador administrador) throws ReglaDeNegocioExcepcion {
        
        String numeroPersonal = administrador.getNumeroDePersonal();
        String nombre = administrador.getNombre();
        String apellidoPaterno = administrador.getApellidoPaterno();
        String apellidoMaterno = administrador.getApellidoMaterno();
        
        int longitudMaximaNumeroPersonal = 5;
        
        if( numeroPersonal.length() != longitudMaximaNumeroPersonal || !numeroPersonal.chars()
           .allMatch(Character::isDigit) ){
            
           throw new ReglaDeNegocioExcepcion("Numero de personal no valido. Debe contener" + 
           longitudMaximaNumeroPersonal + "digitos.");
           
        }
        
        ValidacionDatos validacionDatosPersonales = new ValidacionDatos();
        
        validacionDatosPersonales.validarNombre(nombre);
        validacionDatosPersonales.validarApellidoPaterno(apellidoPaterno);
        validacionDatosPersonales.validarApellidoMaterno(apellidoMaterno);
        
    }
    
}

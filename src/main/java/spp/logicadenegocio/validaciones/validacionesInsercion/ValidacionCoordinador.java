/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import spp.logicadenegocio.clasesdto.Coordinador;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionCoordinador {
        
    public void sonCamposValidosPorReglaNegocio(Coordinador coordinador) throws ReglaDeNegocioExcepcion {
        
        String numeroPersonal = coordinador.getNumeroDePersonal();
        String nombre = coordinador.getNombre();
        String apellidoPaterno = coordinador.getApellidoPaterno();
        String apellidoMaterno = coordinador.getApellidoMaterno();
   
        ValidacionDatos validacionDatosPersonales = new ValidacionDatos();
        
        validacionDatosPersonales.validarNombre(nombre);
        validacionDatosPersonales.validarApellidoPaterno(apellidoPaterno);
        validacionDatosPersonales.validarApellidoMaterno(apellidoMaterno);
        
        int longitudMaximaNumeroPersonal = 5;
        
        if( numeroPersonal.length() != longitudMaximaNumeroPersonal || !numeroPersonal.chars()
           .allMatch(Character::isDigit) ){
            
           throw new ReglaDeNegocioExcepcion("Numero de personal no valido. Debe contener" + 
           longitudMaximaNumeroPersonal + "digitos.");
           
        }
    }
    
}

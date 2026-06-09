/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import spp.logicadenegocio.clasesdto.Profesor;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;


/**
 *
 * @author gomes
 */
public class ValidacionProfesor {
    
    private static final int LONGITUD_MAXIMA_NUMEROPERSONAL = 5;      
    
    public void sonCamposValidosPorReglaNegocio(Profesor profesor) throws ReglaDeNegocioExcepcion {
        
        String numeroPersonal = profesor.getNumeroDePersonal();
        String nombre = profesor.getNombre();
        String apellidoPaterno = profesor.getApellidoPaterno();
        String apellidoMaterno = profesor.getApellidoMaterno();
        
        if(numeroPersonal.length() != LONGITUD_MAXIMA_NUMEROPERSONAL || !numeroPersonal.chars()
        .allMatch(Character::isDigit)){
            
           throw new ReglaDeNegocioExcepcion("Numero de personal no valido. Debe contener" + 
           LONGITUD_MAXIMA_NUMEROPERSONAL +  "digitos.");
           
        }
        
        ValidacionDatos validacionDatosPersonales = new ValidacionDatos();
        
        validacionDatosPersonales.validarNombre(nombre);
        validacionDatosPersonales.validarApellidoPaterno(apellidoPaterno);
        validacionDatosPersonales.validarApellidoMaterno(apellidoMaterno);
        
    }
    
}

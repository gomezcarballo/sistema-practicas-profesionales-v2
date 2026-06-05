/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionDatos {
    
    private static final int LONGITUD_MAXIMA_NOMBRE = 50;
            
    private static final int LONGITUD_MAXIMA_APELLIDO = 30;
    
    public void validarNombre(String nombre) throws ReglaDeNegocioExcepcion{
        
    
        if(!(nombre.matches("^[\\p{L} ]+$"))){

            throw new ReglaDeNegocioExcepcion("El nombre solo debe contener letras.");

        }

        if(nombre.length() > LONGITUD_MAXIMA_NOMBRE){

            throw new ReglaDeNegocioExcepcion("El nombre excede la longitud maxima de " + 
            LONGITUD_MAXIMA_NOMBRE + " caracteres");

        }
    }
    
    public void validarApellidoPaterno(String apellidoPaterno) throws ReglaDeNegocioExcepcion{
        
        if(!(apellidoPaterno.matches("^[\\p{L} ]+$"))){

            throw new ReglaDeNegocioExcepcion("El apellido paterno solo debe contener letras.");

        }

        if(apellidoPaterno.length() > LONGITUD_MAXIMA_APELLIDO){

            throw new ReglaDeNegocioExcepcion("El apellido paterno excede la longitud maxima de " + 
            LONGITUD_MAXIMA_APELLIDO +" caracteres.");

        }
        
    }

    public void validarApellidoMaterno(String apellidoMaterno) throws ReglaDeNegocioExcepcion{
        
        if(apellidoMaterno != null && !(apellidoMaterno.matches("^[\\p{L} ]+$"))){

            throw new ReglaDeNegocioExcepcion("El apellido materno solo debe contener letras.");

        }

        if (apellidoMaterno != null && apellidoMaterno.length() > LONGITUD_MAXIMA_APELLIDO) {

            throw new ReglaDeNegocioExcepcion("El apellido materno excede la longitud máxima de " + 
            LONGITUD_MAXIMA_APELLIDO +" caracteres.");

        }
        
    }
}

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
                    
    public void validarNombre(String nombre) throws ReglaDeNegocioExcepcion{
        
        int longitudMaximaNombre = 50;
        
        if(!(nombre.matches("^[\\p{L} ]+$"))){

            throw new ReglaDeNegocioExcepcion("El nombre solo debe contener letras.");

        }

        if(nombre.length() > longitudMaximaNombre){

            throw new ReglaDeNegocioExcepcion("El nombre excede la longitud maxima de " + 
            longitudMaximaNombre + " caracteres");

        }
    }
    
    public void validarApellidoPaterno(String apellidoPaterno) throws ReglaDeNegocioExcepcion{
        
        int longitudMaximaApellidoPaterno = 30;
        
        if(!(apellidoPaterno.matches("^[\\p{L} ]+$"))){

            throw new ReglaDeNegocioExcepcion("El apellido paterno solo debe contener letras.");

        }

        if(apellidoPaterno.length() > longitudMaximaApellidoPaterno){

            throw new ReglaDeNegocioExcepcion("El apellido paterno excede la longitud maxima de " + 
            longitudMaximaApellidoPaterno +" caracteres.");

        }
        
    }

    public void validarApellidoMaterno(String apellidoMaterno) throws ReglaDeNegocioExcepcion{
        
        int longitudMaximaApellidoMaterno = 30;
        
        if(apellidoMaterno == null || apellidoMaterno.isBlank()){
            return;
        }
        
        if(!apellidoMaterno.matches("^[\\p{L} ]+$")){

            throw new ReglaDeNegocioExcepcion("El apellido materno solo debe contener letras.");

        }

        if (apellidoMaterno.length() > longitudMaximaApellidoMaterno) {

            throw new ReglaDeNegocioExcepcion("El apellido materno excede la longitud máxima de " + 
            longitudMaximaApellidoMaterno +" caracteres.");

        }    
        
    }
    
}

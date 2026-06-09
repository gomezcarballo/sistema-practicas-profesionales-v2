/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import spp.logicadenegocio.clasesdto.Evaluacion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;


/**
 *
 * @author gomes
 */
public class ValidacionEvaluacion {
    
    public void sonCamposValidosPorReglasDeNegocio(Evaluacion evaluacion)throws ReglaDeNegocioExcepcion{
        
        String descripcion = evaluacion.getObservaciones();
        double calificacionFinal = evaluacion.getCalificacionFinal();
        
        int longitudMaximaDescripcion = 100;
        
        if(descripcion.length() > longitudMaximaDescripcion){

            throw new ReglaDeNegocioExcepcion("La descripción excede la longitud maxima de " + 
            longitudMaximaDescripcion + " caracteres");

        }
        
        if (calificacionFinal < 1 || calificacionFinal > 10) {
                
            throw new ReglaDeNegocioExcepcion("La calificacion debe estar en el rango del 1 al 10");   
                
        }
        
    }
    
}

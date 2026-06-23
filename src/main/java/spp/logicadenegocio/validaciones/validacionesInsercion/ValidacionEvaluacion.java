/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.Evaluacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;


/**
 *
 * @author gomes
 */
public class ValidacionEvaluacion {
    
    private final int LONGITUD_MAXIMA_DESCRIPCION = 100;
    private final double CALIFICACION_MINIMA = 1.0;
    private final double CALIFICACION_MAXIMA = 10.0;
    
    public List<String> validarRegistroEvaluacion(Evaluacion evaluacion)throws OperacionesDeDaoExcepcion{
        
        
        List<String> listaErrores = new ArrayList<>();
        
        String descripcion = evaluacion.getObservaciones();
        double calificacionFinal = evaluacion.getCalificacionFinal();
        
        if (descripcion != null && descripcion.length() > LONGITUD_MAXIMA_DESCRIPCION) {
            
            listaErrores.add("La descripción excede la longitud maxima de " + LONGITUD_MAXIMA_DESCRIPCION + " caracteres.");
            
        }
        
        if (calificacionFinal < CALIFICACION_MINIMA || calificacionFinal > CALIFICACION_MAXIMA) {
                
            listaErrores.add("La calificación debe estar en el rango de " + CALIFICACION_MINIMA + " a " + CALIFICACION_MAXIMA + ".");   
                
        }
        
        return listaErrores;
        
    }

    
}

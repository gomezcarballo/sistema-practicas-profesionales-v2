/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import spp.logicadenegocio.clasesdto.Actividad;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionActividad {
    
    public void sonCamposValidosPorReglaDeNegocio(Actividad actividad) throws ReglaDeNegocioExcepcion{
        
        String titulo = actividad.getTitulo();
        String descripcion = actividad.getDescripcion();
        
        int longitudMaximaTitulo = 50;
        int longitudMaximaDescripcion = 100;
        
        if(titulo.length() > longitudMaximaTitulo){
            
            throw new ReglaDeNegocioExcepcion("El título excede la longitud maxima de " + 
            longitudMaximaTitulo + " caracteres");
            
        }   
        
        if(descripcion.length() > longitudMaximaDescripcion){
            
            throw new ReglaDeNegocioExcepcion("La descripción excede la longitud maxima de " + 
            longitudMaximaDescripcion + " caracteres");
            
        } 
        
    }
    
}

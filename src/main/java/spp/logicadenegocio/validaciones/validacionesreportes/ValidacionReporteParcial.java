/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesreportes;

import spp.logicadenegocio.clasesdto.ActividadReporteParcial;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionReporteParcial {
    
    public void validarTamañoActividad(ActividadReporteParcial actividad) throws ReglaDeNegocioExcepcion {
        
        int maximoCaracteresDescripcion = 250;
        
        if (actividad.getDescripcion() != null && actividad.getDescripcion().length() > maximoCaracteresDescripcion) {
            
            throw new ReglaDeNegocioExcepcion("La descripción de la actividad excede el tamaño máximo permitido de " 
            + maximoCaracteresDescripcion + " caracteres.");
            
        }

        int tamanoRequeridoSemanas = 8;
        
        if (actividad.getSemanasPlan() == null || actividad.getSemanasPlan().length < tamanoRequeridoSemanas) {
            
            throw new ReglaDeNegocioExcepcion("El sistema detectó un error en la estructura de semanas planeadas. " 
            + "Contacte a profesor.");
            
        }

        if (actividad.getSemanasReal() == null || actividad.getSemanasReal().length < tamanoRequeridoSemanas) {
            
            throw new ReglaDeNegocioExcepcion("El sistema detectó un error en la estructura de semanas reales. " 
            + "Contacte a su profesor.");
            
        }
        
    }

    public void validarTamañoReporte(String periodo, String resultados, String observaciones) throws ReglaDeNegocioExcepcion {
        
        int maximoCaracteresPeriodo = 100;
        
        if (periodo != null && periodo.length() > maximoCaracteresPeriodo) {
            
            throw new ReglaDeNegocioExcepcion("El periodo excede el tamaño máximo permitido de " 
            + maximoCaracteresPeriodo + " caracteres.");
            
        }

        int maximoCaracteresResultados = 1000;
        
        if (resultados != null && resultados.length() > maximoCaracteresResultados) {
            
            throw new ReglaDeNegocioExcepcion("Los resultados exceden el tamaño máximo permitido de " 
            + maximoCaracteresResultados + " caracteres.");
            
        }

        int maximoCaracteresObservaciones = 1000;
        
        if (observaciones != null && observaciones.length() > maximoCaracteresObservaciones) {
            
            throw new ReglaDeNegocioExcepcion("Las observaciones exceden el tamaño máximo permitido de " 
            + maximoCaracteresObservaciones + " caracteres.");
            
        }
        
    }
    
}
